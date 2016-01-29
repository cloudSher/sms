package com.lashou.service.sms.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public CallerExample() {
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

        public Criteria andCalleridIsNull() {
            addCriterion("callerId is null");
            return (Criteria) this;
        }

        public Criteria andCalleridIsNotNull() {
            addCriterion("callerId is not null");
            return (Criteria) this;
        }

        public Criteria andCalleridEqualTo(Integer value) {
            addCriterion("callerId =", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridNotEqualTo(Integer value) {
            addCriterion("callerId <>", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridGreaterThan(Integer value) {
            addCriterion("callerId >", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridGreaterThanOrEqualTo(Integer value) {
            addCriterion("callerId >=", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridLessThan(Integer value) {
            addCriterion("callerId <", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridLessThanOrEqualTo(Integer value) {
            addCriterion("callerId <=", value, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridIn(List<Integer> values) {
            addCriterion("callerId in", values, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridNotIn(List<Integer> values) {
            addCriterion("callerId not in", values, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridBetween(Integer value1, Integer value2) {
            addCriterion("callerId between", value1, value2, "callerid");
            return (Criteria) this;
        }

        public Criteria andCalleridNotBetween(Integer value1, Integer value2) {
            addCriterion("callerId not between", value1, value2, "callerid");
            return (Criteria) this;
        }

        public Criteria andCallernameIsNull() {
            addCriterion("callerName is null");
            return (Criteria) this;
        }

        public Criteria andCallernameIsNotNull() {
            addCriterion("callerName is not null");
            return (Criteria) this;
        }

        public Criteria andCallernameEqualTo(String value) {
            addCriterion("callerName =", value, "callername");
            return (Criteria) this;
        }

        public Criteria andCallernameNotEqualTo(String value) {
            addCriterion("callerName <>", value, "callername");
            return (Criteria) this;
        }

        public Criteria andCallernameGreaterThan(String value) {
            addCriterion("callerName >", value, "callername");
            return (Criteria) this;
        }

        public Criteria andCallernameGreaterThanOrEqualTo(String value) {
            addCriterion("callerName >=", value, "callername");
            return (Criteria) this;
        }

        public Criteria andCallernameLessThan(String value) {
            addCriterion("callerName <", value, "callername");
            return (Criteria) this;
        }

        public Criteria andCallernameLessThanOrEqualTo(String value) {
            addCriterion("callerName <=", value, "callername");
            return (Criteria) this;
        }

        public Criteria andCallernameLike(String value) {
            addCriterion("callerName like", value, "callername");
            return (Criteria) this;
        }

        public Criteria andCallernameNotLike(String value) {
            addCriterion("callerName not like", value, "callername");
            return (Criteria) this;
        }

        public Criteria andCallernameIn(List<String> values) {
            addCriterion("callerName in", values, "callername");
            return (Criteria) this;
        }

        public Criteria andCallernameNotIn(List<String> values) {
            addCriterion("callerName not in", values, "callername");
            return (Criteria) this;
        }

        public Criteria andCallernameBetween(String value1, String value2) {
            addCriterion("callerName between", value1, value2, "callername");
            return (Criteria) this;
        }

        public Criteria andCallernameNotBetween(String value1, String value2) {
            addCriterion("callerName not between", value1, value2, "callername");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andCalltimeIsNull() {
            addCriterion("callTime is null");
            return (Criteria) this;
        }

        public Criteria andCalltimeIsNotNull() {
            addCriterion("callTime is not null");
            return (Criteria) this;
        }

        public Criteria andCalltimeEqualTo(Date value) {
            addCriterion("callTime =", value, "calltime");
            return (Criteria) this;
        }

        public Criteria andCalltimeNotEqualTo(Date value) {
            addCriterion("callTime <>", value, "calltime");
            return (Criteria) this;
        }

        public Criteria andCalltimeGreaterThan(Date value) {
            addCriterion("callTime >", value, "calltime");
            return (Criteria) this;
        }

        public Criteria andCalltimeGreaterThanOrEqualTo(Date value) {
            addCriterion("callTime >=", value, "calltime");
            return (Criteria) this;
        }

        public Criteria andCalltimeLessThan(Date value) {
            addCriterion("callTime <", value, "calltime");
            return (Criteria) this;
        }

        public Criteria andCalltimeLessThanOrEqualTo(Date value) {
            addCriterion("callTime <=", value, "calltime");
            return (Criteria) this;
        }

        public Criteria andCalltimeIn(List<Date> values) {
            addCriterion("callTime in", values, "calltime");
            return (Criteria) this;
        }

        public Criteria andCalltimeNotIn(List<Date> values) {
            addCriterion("callTime not in", values, "calltime");
            return (Criteria) this;
        }

        public Criteria andCalltimeBetween(Date value1, Date value2) {
            addCriterion("callTime between", value1, value2, "calltime");
            return (Criteria) this;
        }

        public Criteria andCalltimeNotBetween(Date value1, Date value2) {
            addCriterion("callTime not between", value1, value2, "calltime");
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