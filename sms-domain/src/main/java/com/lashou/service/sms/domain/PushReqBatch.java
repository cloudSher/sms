package com.lashou.service.sms.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sher on 1/20/16.
 */
public class PushReqBatch extends PushReq{

    private List<PushReqSingle> batch;


    public List<PushReqSingle> getBatch() {
        return batch;
    }

    public void setBatch(List<PushReqSingle> batch) {
        this.batch = batch;
    }
}
