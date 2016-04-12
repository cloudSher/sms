package com.lashou.service.sms.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageResponseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public MessageResponseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMessageidIsNull() {
            addCriterion("messageId is null");
            return (Criteria) this;
        }

        public Criteria andMessageidIsNotNull() {
            addCriterion("messageId is not null");
            return (Criteria) this;
        }

        public Criteria andMessageidEqualTo(String value) {
            addCriterion("messageId =", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidNotEqualTo(String value) {
            addCriterion("messageId <>", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidGreaterThan(String value) {
            addCriterion("messageId >", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidGreaterThanOrEqualTo(String value) {
            addCriterion("messageId >=", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidLessThan(String value) {
            addCriterion("messageId <", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidLessThanOrEqualTo(String value) {
            addCriterion("messageId <=", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidLike(String value) {
            addCriterion("messageId like", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidNotLike(String value) {
            addCriterion("messageId not like", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidIn(List<String> values) {
            addCriterion("messageId in", values, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidNotIn(List<String> values) {
            addCriterion("messageId not in", values, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidBetween(String value1, String value2) {
            addCriterion("messageId between", value1, value2, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidNotBetween(String value1, String value2) {
            addCriterion("messageId not between", value1, value2, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessagenameIsNull() {
            addCriterion("messageName is null");
            return (Criteria) this;
        }

        public Criteria andMessagenameIsNotNull() {
            addCriterion("messageName is not null");
            return (Criteria) this;
        }

        public Criteria andMessagenameEqualTo(String value) {
            addCriterion("messageName =", value, "messagename");
            return (Criteria) this;
        }

        public Criteria andMessagenameNotEqualTo(String value) {
            addCriterion("messageName <>", value, "messagename");
            return (Criteria) this;
        }

        public Criteria andMessagenameGreaterThan(String value) {
            addCriterion("messageName >", value, "messagename");
            return (Criteria) this;
        }

        public Criteria andMessagenameGreaterThanOrEqualTo(String value) {
            addCriterion("messageName >=", value, "messagename");
            return (Criteria) this;
        }

        public Criteria andMessagenameLessThan(String value) {
            addCriterion("messageName <", value, "messagename");
            return (Criteria) this;
        }

        public Criteria andMessagenameLessThanOrEqualTo(String value) {
            addCriterion("messageName <=", value, "messagename");
            return (Criteria) this;
        }

        public Criteria andMessagenameLike(String value) {
            addCriterion("messageName like", value, "messagename");
            return (Criteria) this;
        }

        public Criteria andMessagenameNotLike(String value) {
            addCriterion("messageName not like", value, "messagename");
            return (Criteria) this;
        }

        public Criteria andMessagenameIn(List<String> values) {
            addCriterion("messageName in", values, "messagename");
            return (Criteria) this;
        }

        public Criteria andMessagenameNotIn(List<String> values) {
            addCriterion("messageName not in", values, "messagename");
            return (Criteria) this;
        }

        public Criteria andMessagenameBetween(String value1, String value2) {
            addCriterion("messageName between", value1, value2, "messagename");
            return (Criteria) this;
        }

        public Criteria andMessagenameNotBetween(String value1, String value2) {
            addCriterion("messageName not between", value1, value2, "messagename");
            return (Criteria) this;
        }

        public Criteria andMobilesIsNull() {
            addCriterion("mobiles is null");
            return (Criteria) this;
        }

        public Criteria andMobilesIsNotNull() {
            addCriterion("mobiles is not null");
            return (Criteria) this;
        }

        public Criteria andMobilesEqualTo(String value) {
            addCriterion("mobiles =", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesNotEqualTo(String value) {
            addCriterion("mobiles <>", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesGreaterThan(String value) {
            addCriterion("mobiles >", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesGreaterThanOrEqualTo(String value) {
            addCriterion("mobiles >=", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesLessThan(String value) {
            addCriterion("mobiles <", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesLessThanOrEqualTo(String value) {
            addCriterion("mobiles <=", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesLike(String value) {
            addCriterion("mobiles like", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesNotLike(String value) {
            addCriterion("mobiles not like", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesIn(List<String> values) {
            addCriterion("mobiles in", values, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesNotIn(List<String> values) {
            addCriterion("mobiles not in", values, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesBetween(String value1, String value2) {
            addCriterion("mobiles between", value1, value2, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesNotBetween(String value1, String value2) {
            addCriterion("mobiles not between", value1, value2, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorIsNull() {
            addCriterion("mobiles_operator is null");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorIsNotNull() {
            addCriterion("mobiles_operator is not null");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorEqualTo(String value) {
            addCriterion("mobiles_operator =", value, "mobilesOperator");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorNotEqualTo(String value) {
            addCriterion("mobiles_operator <>", value, "mobilesOperator");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorGreaterThan(String value) {
            addCriterion("mobiles_operator >", value, "mobilesOperator");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("mobiles_operator >=", value, "mobilesOperator");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorLessThan(String value) {
            addCriterion("mobiles_operator <", value, "mobilesOperator");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorLessThanOrEqualTo(String value) {
            addCriterion("mobiles_operator <=", value, "mobilesOperator");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorLike(String value) {
            addCriterion("mobiles_operator like", value, "mobilesOperator");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorNotLike(String value) {
            addCriterion("mobiles_operator not like", value, "mobilesOperator");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorIn(List<String> values) {
            addCriterion("mobiles_operator in", values, "mobilesOperator");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorNotIn(List<String> values) {
            addCriterion("mobiles_operator not in", values, "mobilesOperator");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorBetween(String value1, String value2) {
            addCriterion("mobiles_operator between", value1, value2, "mobilesOperator");
            return (Criteria) this;
        }

        public Criteria andMobilesOperatorNotBetween(String value1, String value2) {
            addCriterion("mobiles_operator not between", value1, value2, "mobilesOperator");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIsNull() {
            addCriterion("operator_type is null");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIsNotNull() {
            addCriterion("operator_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeEqualTo(Integer value) {
            addCriterion("operator_type =", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotEqualTo(Integer value) {
            addCriterion("operator_type <>", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeGreaterThan(Integer value) {
            addCriterion("operator_type >", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator_type >=", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLessThan(Integer value) {
            addCriterion("operator_type <", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLessThanOrEqualTo(Integer value) {
            addCriterion("operator_type <=", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIn(List<Integer> values) {
            addCriterion("operator_type in", values, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotIn(List<Integer> values) {
            addCriterion("operator_type not in", values, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeBetween(Integer value1, Integer value2) {
            addCriterion("operator_type between", value1, value2, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("operator_type not between", value1, value2, "operatorType");
            return (Criteria) this;
        }

        public Criteria andChannelsnameIsNull() {
            addCriterion("channelsName is null");
            return (Criteria) this;
        }

        public Criteria andChannelsnameIsNotNull() {
            addCriterion("channelsName is not null");
            return (Criteria) this;
        }

        public Criteria andChannelsnameEqualTo(String value) {
            addCriterion("channelsName =", value, "channelsname");
            return (Criteria) this;
        }

        public Criteria andChannelsnameNotEqualTo(String value) {
            addCriterion("channelsName <>", value, "channelsname");
            return (Criteria) this;
        }

        public Criteria andChannelsnameGreaterThan(String value) {
            addCriterion("channelsName >", value, "channelsname");
            return (Criteria) this;
        }

        public Criteria andChannelsnameGreaterThanOrEqualTo(String value) {
            addCriterion("channelsName >=", value, "channelsname");
            return (Criteria) this;
        }

        public Criteria andChannelsnameLessThan(String value) {
            addCriterion("channelsName <", value, "channelsname");
            return (Criteria) this;
        }

        public Criteria andChannelsnameLessThanOrEqualTo(String value) {
            addCriterion("channelsName <=", value, "channelsname");
            return (Criteria) this;
        }

        public Criteria andChannelsnameLike(String value) {
            addCriterion("channelsName like", value, "channelsname");
            return (Criteria) this;
        }

        public Criteria andChannelsnameNotLike(String value) {
            addCriterion("channelsName not like", value, "channelsname");
            return (Criteria) this;
        }

        public Criteria andChannelsnameIn(List<String> values) {
            addCriterion("channelsName in", values, "channelsname");
            return (Criteria) this;
        }

        public Criteria andChannelsnameNotIn(List<String> values) {
            addCriterion("channelsName not in", values, "channelsname");
            return (Criteria) this;
        }

        public Criteria andChannelsnameBetween(String value1, String value2) {
            addCriterion("channelsName between", value1, value2, "channelsname");
            return (Criteria) this;
        }

        public Criteria andChannelsnameNotBetween(String value1, String value2) {
            addCriterion("channelsName not between", value1, value2, "channelsname");
            return (Criteria) this;
        }

        public Criteria andChannelsidIsNull() {
            addCriterion("channelsId is null");
            return (Criteria) this;
        }

        public Criteria andChannelsidIsNotNull() {
            addCriterion("channelsId is not null");
            return (Criteria) this;
        }

        public Criteria andChannelsidEqualTo(String value) {
            addCriterion("channelsId =", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidNotEqualTo(String value) {
            addCriterion("channelsId <>", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidGreaterThan(String value) {
            addCriterion("channelsId >", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidGreaterThanOrEqualTo(String value) {
            addCriterion("channelsId >=", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidLessThan(String value) {
            addCriterion("channelsId <", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidLessThanOrEqualTo(String value) {
            addCriterion("channelsId <=", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidLike(String value) {
            addCriterion("channelsId like", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidNotLike(String value) {
            addCriterion("channelsId not like", value, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidIn(List<String> values) {
            addCriterion("channelsId in", values, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidNotIn(List<String> values) {
            addCriterion("channelsId not in", values, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidBetween(String value1, String value2) {
            addCriterion("channelsId between", value1, value2, "channelsid");
            return (Criteria) this;
        }

        public Criteria andChannelsidNotBetween(String value1, String value2) {
            addCriterion("channelsId not between", value1, value2, "channelsid");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andCurrenttimeIsNull() {
            addCriterion("currentTime is null");
            return (Criteria) this;
        }

        public Criteria andCurrenttimeIsNotNull() {
            addCriterion("currentTime is not null");
            return (Criteria) this;
        }

        public Criteria andCurrenttimeEqualTo(Date value) {
            addCriterion("currentTime =", value, "currenttime");
            return (Criteria) this;
        }

        public Criteria andCurrenttimeNotEqualTo(Date value) {
            addCriterion("currentTime <>", value, "currenttime");
            return (Criteria) this;
        }

        public Criteria andCurrenttimeGreaterThan(Date value) {
            addCriterion("currentTime >", value, "currenttime");
            return (Criteria) this;
        }

        public Criteria andCurrenttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("currentTime >=", value, "currenttime");
            return (Criteria) this;
        }

        public Criteria andCurrenttimeLessThan(Date value) {
            addCriterion("currentTime <", value, "currenttime");
            return (Criteria) this;
        }

        public Criteria andCurrenttimeLessThanOrEqualTo(Date value) {
            addCriterion("currentTime <=", value, "currenttime");
            return (Criteria) this;
        }

        public Criteria andCurrenttimeIn(List<Date> values) {
            addCriterion("currentTime in", values, "currenttime");
            return (Criteria) this;
        }

        public Criteria andCurrenttimeNotIn(List<Date> values) {
            addCriterion("currentTime not in", values, "currenttime");
            return (Criteria) this;
        }

        public Criteria andCurrenttimeBetween(Date value1, Date value2) {
            addCriterion("currentTime between", value1, value2, "currenttime");
            return (Criteria) this;
        }

        public Criteria andCurrenttimeNotBetween(Date value1, Date value2) {
            addCriterion("currentTime not between", value1, value2, "currenttime");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeIsNull() {
            addCriterion("channels_response_code is null");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeIsNotNull() {
            addCriterion("channels_response_code is not null");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeEqualTo(String value) {
            addCriterion("channels_response_code =", value, "channelsResponseCode");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeNotEqualTo(String value) {
            addCriterion("channels_response_code <>", value, "channelsResponseCode");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeGreaterThan(String value) {
            addCriterion("channels_response_code >", value, "channelsResponseCode");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("channels_response_code >=", value, "channelsResponseCode");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeLessThan(String value) {
            addCriterion("channels_response_code <", value, "channelsResponseCode");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeLessThanOrEqualTo(String value) {
            addCriterion("channels_response_code <=", value, "channelsResponseCode");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeLike(String value) {
            addCriterion("channels_response_code like", value, "channelsResponseCode");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeNotLike(String value) {
            addCriterion("channels_response_code not like", value, "channelsResponseCode");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeIn(List<String> values) {
            addCriterion("channels_response_code in", values, "channelsResponseCode");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeNotIn(List<String> values) {
            addCriterion("channels_response_code not in", values, "channelsResponseCode");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeBetween(String value1, String value2) {
            addCriterion("channels_response_code between", value1, value2, "channelsResponseCode");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseCodeNotBetween(String value1, String value2) {
            addCriterion("channels_response_code not between", value1, value2, "channelsResponseCode");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseTimeIsNull() {
            addCriterion("channels_response_time is null");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseTimeIsNotNull() {
            addCriterion("channels_response_time is not null");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseTimeEqualTo(Integer value) {
            addCriterion("channels_response_time =", value, "channelsResponseTime");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseTimeNotEqualTo(Integer value) {
            addCriterion("channels_response_time <>", value, "channelsResponseTime");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseTimeGreaterThan(Integer value) {
            addCriterion("channels_response_time >", value, "channelsResponseTime");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("channels_response_time >=", value, "channelsResponseTime");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseTimeLessThan(Integer value) {
            addCriterion("channels_response_time <", value, "channelsResponseTime");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseTimeLessThanOrEqualTo(Integer value) {
            addCriterion("channels_response_time <=", value, "channelsResponseTime");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseTimeIn(List<Integer> values) {
            addCriterion("channels_response_time in", values, "channelsResponseTime");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseTimeNotIn(List<Integer> values) {
            addCriterion("channels_response_time not in", values, "channelsResponseTime");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseTimeBetween(Integer value1, Integer value2) {
            addCriterion("channels_response_time between", value1, value2, "channelsResponseTime");
            return (Criteria) this;
        }

        public Criteria andChannelsResponseTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("channels_response_time not between", value1, value2, "channelsResponseTime");
            return (Criteria) this;
        }

        public Criteria andIssuccessIsNull() {
            addCriterion("isSuccess is null");
            return (Criteria) this;
        }

        public Criteria andIssuccessIsNotNull() {
            addCriterion("isSuccess is not null");
            return (Criteria) this;
        }

        public Criteria andIssuccessEqualTo(Byte value) {
            addCriterion("isSuccess =", value, "issuccess");
            return (Criteria) this;
        }

        public Criteria andIssuccessNotEqualTo(Byte value) {
            addCriterion("isSuccess <>", value, "issuccess");
            return (Criteria) this;
        }

        public Criteria andIssuccessGreaterThan(Byte value) {
            addCriterion("isSuccess >", value, "issuccess");
            return (Criteria) this;
        }

        public Criteria andIssuccessGreaterThanOrEqualTo(Byte value) {
            addCriterion("isSuccess >=", value, "issuccess");
            return (Criteria) this;
        }

        public Criteria andIssuccessLessThan(Byte value) {
            addCriterion("isSuccess <", value, "issuccess");
            return (Criteria) this;
        }

        public Criteria andIssuccessLessThanOrEqualTo(Byte value) {
            addCriterion("isSuccess <=", value, "issuccess");
            return (Criteria) this;
        }

        public Criteria andIssuccessIn(List<Byte> values) {
            addCriterion("isSuccess in", values, "issuccess");
            return (Criteria) this;
        }

        public Criteria andIssuccessNotIn(List<Byte> values) {
            addCriterion("isSuccess not in", values, "issuccess");
            return (Criteria) this;
        }

        public Criteria andIssuccessBetween(Byte value1, Byte value2) {
            addCriterion("isSuccess between", value1, value2, "issuccess");
            return (Criteria) this;
        }

        public Criteria andIssuccessNotBetween(Byte value1, Byte value2) {
            addCriterion("isSuccess not between", value1, value2, "issuccess");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}