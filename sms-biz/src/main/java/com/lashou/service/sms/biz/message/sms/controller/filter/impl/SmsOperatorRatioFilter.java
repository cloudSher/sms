package com.lashou.service.sms.biz.message.sms.controller.filter.impl;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.config.impl.ChannelsAccount;
import com.lashou.service.sms.biz.message.sms.common.StringUtil;
import com.lashou.service.sms.biz.message.sms.controller.filter.Filter;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import com.lashou.service.sms.biz.message.sms.model.SmsOperatorType;
import com.lashou.service.sms.biz.message.sms.model.SmsRequestMsg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cloudsher on 2016/3/15.
 */
public class SmsOperatorRatioFilter implements Filter {


    @Override
    public Result invoke(Invoker invoker, Invocation invocation) {
        SmsRequestMsg msg = invocation.getAttachment();
        List<String> cmcList = new ArrayList<>();
        List<String> cucList = new ArrayList<>();
        List<String> ctcList = new ArrayList<>();

        if(msg ==null){
            try {
                throw new InvalidArgumentException("消息体不能为空");
            } catch (InvalidArgumentException e) {
                e.printStackTrace();
            }
        }
        operatorSharding(msg,cmcList,cucList,ctcList);

        List<Channels> channels = invocation.getContainer().getChannels();
        SiftChannels siftChannels = new SiftChannels(cmcList, cucList, ctcList, channels).invoke().getChannels(msg);
        invocation.setChannels(siftChannels.getSendChannels());
        invocation.setMsgList(siftChannels.getSmsMsgList());
        return invoker.invoke(invocation);
    }

    public Object[] sort(int[] ratio,String[] cId){
        for(int i = 0 ; i < ratio.length-1; i++){
            for(int j = i + 1 ; j < ratio.length; j++){
                if(ratio[i] > ratio[j]){
                    int temp = ratio[i];
                    ratio[i] = ratio[j];
                    ratio[j] = temp;
                    String tmp = cId[i];
                    cId[i] = cId[j];
                    cId[j] = tmp;
                }
            }
        }
        //处理多余的0
        int len = 0,l =0;
        int temp[] = new int[ratio.length];
        String[] tmp = new String[ratio.length];

        for(int k = 0 ; k < ratio.length; k++){
            if(ratio[k] !=0){
                temp[len++] = ratio[k];
                tmp[l++] = cId[k];
            }

        }
        int arr[] = new int[len];
        String arr1[] = new String[l];
        System.arraycopy(temp,0,arr,0,len);
        System.arraycopy(tmp,0,arr1,0,l);

        Object[] obj = new Object[]{arr,arr1};
        return obj;
    }

    public String mobiles(List list){
        String mobiles = "";
        for(int i = 0 ; i< list.size(); i++){
            mobiles += list.get(i);
            if(i < list.size()-1){
                mobiles +=",";
            }
        }
        return mobiles;
    }

    public void operatorSharding(SmsRequestMsg msg,List<String> cmcList,List<String> cucList,List<String> ctcList){
        String mobiles = msg.getMobiles();
        String [] mobileArr;
        if(mobiles.contains(",")){
            mobileArr = mobiles.split(",");
        }else{
            mobileArr = new String[1];
            mobileArr[0] = mobiles;
        }

        for(int i = 0 ; i<mobileArr.length;i++){
            String phone = mobileArr[i];
            if(!StringUtil.isNullOrEmpty(phone))
                if(phone.matches("^1(3[4-9]|5[012789]|8[123478])\\d{8}$")){ //移动手机号
                    cmcList.add(phone);
                }else if(phone.matches("^1(33|53|8[09])\\d{8}$")){ //电信手机号
                    ctcList.add(phone);
                }else if(phone.matches("^1(3[0-2]|5[56]|8[56])\\d{8}$")){  //联通手机号
                    cucList.add(phone);
                }else{
                    cmcList.add(phone);
                }
        }

    }


    public int random(int[] radio){
        int[] linear = new int[radio.length];
        int temp = 0;
        for(int i = 0 ; i< radio.length; i++){
            linear[i] = temp + radio[i];
            temp = linear[i];
        }
        Random random = new Random();
        int rm = random.nextInt(100);
        int index = 0;
        for(int j = 0; j< linear.length; j++){
            if(rm <= linear[j]){
                index = j;
                break;
            }
        }
        return index;
    }


    private class SiftChannels {
        private List<String> cmcList;
        private List<String> cucList;
        private List<String> ctcList;
        private List<Channels> channels;
        private List<SmsRequestMsg> list = new ArrayList<>(3);
        private List<Channels> sendChannels = new ArrayList<>();
        private String cmc;
        private String cuc;
        private String ctc;

        public SiftChannels(List<String> cmcList, List<String> cucList, List<String> ctcList, List<Channels> channels) {
            this.cmcList = cmcList;
            this.cucList = cucList;
            this.ctcList = ctcList;
            this.channels = channels;
        }


        public SiftChannels invoke() {
            int[]  cmcRatio = new int[channels.size()];
            int[]  cucRatio = new int[channels.size()];
            int[]  ctcRatio = new int[cucRatio.length];

            String[] cmId = new String[ctcRatio.length];
            String[] cuId = new String[ctcRatio.length];
            String[] ctId = new String[cmId.length];

            if(channels!=null){
                for(int i = 0 ; i< channels.size(); i++){
                    Channels ch = channels.get(i);
                    cmcRatio[i] = ch.getCMCRatio();
                    cucRatio[i] = ch.getCUCRatio();
                    ctcRatio[i] = ch.getCTCRatio();
                    cmId[i] = ch.getId();
                    cuId[i] = ch.getId();
                    ctId[i] = ch.getId();
                }
            }

            cmc = "";
            if(cmcList.size() >0){
                Object obj[] = sort(cmcRatio,cmId);
                String[] str = (String[]) obj[1];
                cmc = str[random((int[])obj[0])];
            }

            cuc = "";
            if(cucList.size() >0){
                Object obj[] = sort(cucRatio,cuId);
                String[] str = (String[]) obj[1];
                cuc = str[random((int[])obj[0])];
            }

            ctc = "";
            if(ctcList.size() >0){
                Object obj[] = sort(ctcRatio,ctId);
                String[] str = (String[]) obj[1];
                ctc = str[random((int[])obj[0])];
            }
            return this;
        }

        public SiftChannels getChannels(SmsRequestMsg msg){
            SmsRequestMsg requestMsg;
            for(int i = 0 ; i< channels.size(); i++){
                Channels cs = channels.get(i);
                if(cmc!="" && cs.getId().equals(cmc)){
                    if(!cs.isUsed()){
                        cs.setCMCRatio(0);
                        invoke();
                        getChannels(msg);
                    }
                    chooseAccount(cs,msg.getSendScope());
                    requestMsg = new SmsRequestMsg();
                    requestMsg.setChannels(cs);
                    requestMsg.setMessageId(msg.getMessageId());
                    requestMsg.setSendScope(msg.getSendScope());
                    requestMsg.setChannel(cs.getChannelName());
                    requestMsg.setMessage(msg.getMessage());
                    requestMsg.setOperatorType(SmsOperatorType.CMCC);
                    requestMsg.setMobileOperator("cmc");
                    requestMsg.setMobiles(mobiles(cmcList));
                    list.add(requestMsg);
                    sendChannels.add(cs);
                }
                if(cuc!="" && cs.getId().equals(cuc)){
                    if(!cs.isUsed()){
                        cs.setCUCRatio(0);
                        invoke();
                        getChannels(msg);
                    }
                    chooseAccount(cs,msg.getSendScope());
                    requestMsg = new SmsRequestMsg();
                    requestMsg.setChannels(cs);
                    requestMsg.setMessageId(msg.getMessageId());
                    requestMsg.setSendScope(msg.getSendScope());
                    requestMsg.setChannel(cs.getChannelName());
                    requestMsg.setMessage(msg.getMessage());
                    requestMsg.setMobileOperator("cuc");
                    requestMsg.setOperatorType(SmsOperatorType.CUCC);
                    requestMsg.setMobiles(mobiles(cucList));
                    list.add(requestMsg);
                    sendChannels.add(cs);
                }
                if(ctc!="" && cs.getId().equals(ctc)){
                    if(!cs.isUsed()){
                        cs.setCTCRatio(0);
                        invoke();
                        getChannels(msg);
                    }
                    chooseAccount(cs,msg.getSendScope());
                    requestMsg = new SmsRequestMsg();
                    requestMsg.setChannels(cs);
                    requestMsg.setMessageId(msg.getMessageId());
                    requestMsg.setSendScope(msg.getSendScope());
                    requestMsg.setChannel(cs.getChannelName());
                    requestMsg.setMobileOperator("ctc");
                    requestMsg.setOperatorType(SmsOperatorType.CTCC);
                    requestMsg.setMessage(msg.getMessage());
                    requestMsg.setMobiles(mobiles(ctcList));
                    list.add(requestMsg);
                    sendChannels.add(cs);
                }
            }
            return this;
        }


        public List<SmsRequestMsg> getSmsMsgList(){
            return list;
        }

        public List<Channels> getSendChannels(){
            return sendChannels;
        }

        public void chooseAccount(Channels cl,int type){
            if(cl.getAccounts()!=null && cl.getAccounts().size()>1){
                List<ChannelsAccount> accounts = cl.getAccounts();
                for(int i = 0 ; i< accounts.size(); i++){
                    ChannelsAccount account = accounts.get(i);
                    if(account.getType() == type){
                        cl.setAccount(account);
                    }
                }
            }
        }
    }
}
