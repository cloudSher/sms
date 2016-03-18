package com.lashou.service.sms.biz.message.sms.controller.filter.impl;

import com.lashou.service.sms.biz.message.config.impl.Channels;
import com.lashou.service.sms.biz.message.sms.controller.filter.Filter;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invocation;
import com.lashou.service.sms.biz.message.sms.controller.filter.Invoker;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
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
        SiftChannels siftChannels = new SiftChannels(cmcList, cucList, ctcList, channels).invoke();
        return new Result<List>(1,siftChannels.getSmsMsgList());
    }

    public void sort(int[] ratio){
        for(int i = 0 ; i < ratio.length-1; i++){
            for(int j = 1 ; j < ratio.length; j++){
                if(ratio[i] == 0)
                    continue;
                if(ratio[i] > ratio[j]){
                    int temp = ratio[i];
                    ratio[i] = ratio[j];
                    ratio[j] = temp;
                }
            }
        }
    }

    public String mobiles(List list){
        String mobiles = null;
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
            if(phone.matches("^1(3[4-9]|5[012789]|8[78])\\d{8}$")){ //移动手机号
                cmcList.add(phone);
            }else if(phone.matches("^18[09]\\d{8}$")){ //电信手机号
                ctcList.add(phone);
            }else if(phone.matches("^1(3[0-2]|5[56]|8[56])\\d{8}$")){  //联通手机号
                cucList.add(phone);
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
        return radio[index];
    }


    private class SiftChannels {
        private List<String> cmcList;
        private List<String> cucList;
        private List<String> ctcList;
        private List<Channels> channels;
        private List<SmsRequestMsg> list = new ArrayList<>(3);
        private List<Channels> sendChannels;
        private int cmc;
        private int cuc;
        private int ctc;

        public SiftChannels(List<String> cmcList, List<String> cucList, List<String> ctcList, List<Channels> channels) {
            this.cmcList = cmcList;
            this.cucList = cucList;
            this.ctcList = ctcList;
            this.channels = channels;
        }

        public int getCmc() {
            return cmc;
        }

        public int getCuc() {
            return cuc;
        }

        public int getCtc() {
            return ctc;
        }

        public SiftChannels invoke() {
            int[]  cmcRatio = new int[channels.size()];
            int[]  cucRatio = new int[channels.size()];
            int[]  ctcRatio = new int[cucRatio.length];

            if(channels!=null){
                for(int i = 0 ; i< channels.size(); i++){
                    Channels ch = channels.get(i);
                    cmcRatio[i] = ch.getCMCRatio();
                    cucRatio[i] = ch.getCUCRatio();
                    ctcRatio[i] = ch.getCTCRatio();
                }
            }

            cmc = -1;
            if(cmcList.size() >0){
                sort(cmcRatio);
                cmc = random(cmcRatio);
            }

            cuc = -1;
            if(cucList.size() >0){
                sort(cucRatio);
                cuc = random(cucRatio);
            }

            ctc = -1;
            if(ctcList.size() >0){
                sort(ctcRatio);
                ctc = random(ctcRatio);
            }
            return this;
        }

        public SiftChannels getChannels(SmsRequestMsg msg){
            SmsRequestMsg requestMsg;
            for(int i = 0 ; i< channels.size(); i++){
                Channels cs = channels.get(i);
                if(cmc!=-1 && cs.getCMCRatio() == cmc && cmc!=0){
                    if(!cs.isUsed()){
                        cs.setCMCRatio(0);
                        invoke();
                        getChannels(msg);
                    }
                    requestMsg = new SmsRequestMsg();
                    requestMsg.setChannels(cs);
                    requestMsg.setMessage(msg.getMessage());
                    requestMsg.setMobiles(mobiles(cmcList));
                    list.add(requestMsg);
                }else if(cuc!=-1 && cs.getCUCRatio() == cuc && cuc != 0){
                    if(!cs.isUsed()){
                        cs.setCMCRatio(0);
                        invoke();
                        getChannels(msg);
                    }
                    requestMsg = new SmsRequestMsg();
                    requestMsg.setChannels(cs);
                    requestMsg.setMessage(msg.getMessage());
                    requestMsg.setMobiles(mobiles(cucList));
                    list.add(requestMsg);
                }else if(ctc!=-1 && cs.getCTCRatio() == ctc && ctc != 0){
                    if(!cs.isUsed()){
                        cs.setCMCRatio(0);
                        invoke();
                        getChannels(msg);
                    }
                    requestMsg = new SmsRequestMsg();
                    requestMsg.setChannels(cs);
                    requestMsg.setMessage(msg.getMessage());
                    requestMsg.setMobiles(mobiles(ctcList));
                    list.add(requestMsg);
                }
            }
            return this;
        }


        public List<SmsRequestMsg> getSmsMsgList(){
            return list;
        }
    }
}
