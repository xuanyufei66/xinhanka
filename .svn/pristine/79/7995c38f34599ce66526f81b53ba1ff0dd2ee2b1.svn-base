package com.payease.wallet.entity.pojo;

import java.util.ArrayList;
import java.util.List;

public class BaffleInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaffleInfoExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andReqCodeIsNull() {
            addCriterion("req_code is null");
            return (Criteria) this;
        }

        public Criteria andReqCodeIsNotNull() {
            addCriterion("req_code is not null");
            return (Criteria) this;
        }

        public Criteria andReqCodeEqualTo(String value) {
            addCriterion("req_code =", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeNotEqualTo(String value) {
            addCriterion("req_code <>", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeGreaterThan(String value) {
            addCriterion("req_code >", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeGreaterThanOrEqualTo(String value) {
            addCriterion("req_code >=", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeLessThan(String value) {
            addCriterion("req_code <", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeLessThanOrEqualTo(String value) {
            addCriterion("req_code <=", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeLike(String value) {
            addCriterion("req_code like", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeNotLike(String value) {
            addCriterion("req_code not like", value, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeIn(List<String> values) {
            addCriterion("req_code in", values, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeNotIn(List<String> values) {
            addCriterion("req_code not in", values, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeBetween(String value1, String value2) {
            addCriterion("req_code between", value1, value2, "reqCode");
            return (Criteria) this;
        }

        public Criteria andReqCodeNotBetween(String value1, String value2) {
            addCriterion("req_code not between", value1, value2, "reqCode");
            return (Criteria) this;
        }

        public Criteria andRspJsonIsNull() {
            addCriterion("rsp_json is null");
            return (Criteria) this;
        }

        public Criteria andRspJsonIsNotNull() {
            addCriterion("rsp_json is not null");
            return (Criteria) this;
        }

        public Criteria andRspJsonEqualTo(String value) {
            addCriterion("rsp_json =", value, "rspJson");
            return (Criteria) this;
        }

        public Criteria andRspJsonNotEqualTo(String value) {
            addCriterion("rsp_json <>", value, "rspJson");
            return (Criteria) this;
        }

        public Criteria andRspJsonGreaterThan(String value) {
            addCriterion("rsp_json >", value, "rspJson");
            return (Criteria) this;
        }

        public Criteria andRspJsonGreaterThanOrEqualTo(String value) {
            addCriterion("rsp_json >=", value, "rspJson");
            return (Criteria) this;
        }

        public Criteria andRspJsonLessThan(String value) {
            addCriterion("rsp_json <", value, "rspJson");
            return (Criteria) this;
        }

        public Criteria andRspJsonLessThanOrEqualTo(String value) {
            addCriterion("rsp_json <=", value, "rspJson");
            return (Criteria) this;
        }

        public Criteria andRspJsonLike(String value) {
            addCriterion("rsp_json like", value, "rspJson");
            return (Criteria) this;
        }

        public Criteria andRspJsonNotLike(String value) {
            addCriterion("rsp_json not like", value, "rspJson");
            return (Criteria) this;
        }

        public Criteria andRspJsonIn(List<String> values) {
            addCriterion("rsp_json in", values, "rspJson");
            return (Criteria) this;
        }

        public Criteria andRspJsonNotIn(List<String> values) {
            addCriterion("rsp_json not in", values, "rspJson");
            return (Criteria) this;
        }

        public Criteria andRspJsonBetween(String value1, String value2) {
            addCriterion("rsp_json between", value1, value2, "rspJson");
            return (Criteria) this;
        }

        public Criteria andRspJsonNotBetween(String value1, String value2) {
            addCriterion("rsp_json not between", value1, value2, "rspJson");
            return (Criteria) this;
        }

        public Criteria andSupplementIsNull() {
            addCriterion("supplement is null");
            return (Criteria) this;
        }

        public Criteria andSupplementIsNotNull() {
            addCriterion("supplement is not null");
            return (Criteria) this;
        }

        public Criteria andSupplementEqualTo(String value) {
            addCriterion("supplement =", value, "supplement");
            return (Criteria) this;
        }

        public Criteria andSupplementNotEqualTo(String value) {
            addCriterion("supplement <>", value, "supplement");
            return (Criteria) this;
        }

        public Criteria andSupplementGreaterThan(String value) {
            addCriterion("supplement >", value, "supplement");
            return (Criteria) this;
        }

        public Criteria andSupplementGreaterThanOrEqualTo(String value) {
            addCriterion("supplement >=", value, "supplement");
            return (Criteria) this;
        }

        public Criteria andSupplementLessThan(String value) {
            addCriterion("supplement <", value, "supplement");
            return (Criteria) this;
        }

        public Criteria andSupplementLessThanOrEqualTo(String value) {
            addCriterion("supplement <=", value, "supplement");
            return (Criteria) this;
        }

        public Criteria andSupplementLike(String value) {
            addCriterion("supplement like", value, "supplement");
            return (Criteria) this;
        }

        public Criteria andSupplementNotLike(String value) {
            addCriterion("supplement not like", value, "supplement");
            return (Criteria) this;
        }

        public Criteria andSupplementIn(List<String> values) {
            addCriterion("supplement in", values, "supplement");
            return (Criteria) this;
        }

        public Criteria andSupplementNotIn(List<String> values) {
            addCriterion("supplement not in", values, "supplement");
            return (Criteria) this;
        }

        public Criteria andSupplementBetween(String value1, String value2) {
            addCriterion("supplement between", value1, value2, "supplement");
            return (Criteria) this;
        }

        public Criteria andSupplementNotBetween(String value1, String value2) {
            addCriterion("supplement not between", value1, value2, "supplement");
            return (Criteria) this;
        }

        public Criteria andSpareIsNull() {
            addCriterion("spare is null");
            return (Criteria) this;
        }

        public Criteria andSpareIsNotNull() {
            addCriterion("spare is not null");
            return (Criteria) this;
        }

        public Criteria andSpareEqualTo(String value) {
            addCriterion("spare =", value, "spare");
            return (Criteria) this;
        }

        public Criteria andSpareNotEqualTo(String value) {
            addCriterion("spare <>", value, "spare");
            return (Criteria) this;
        }

        public Criteria andSpareGreaterThan(String value) {
            addCriterion("spare >", value, "spare");
            return (Criteria) this;
        }

        public Criteria andSpareGreaterThanOrEqualTo(String value) {
            addCriterion("spare >=", value, "spare");
            return (Criteria) this;
        }

        public Criteria andSpareLessThan(String value) {
            addCriterion("spare <", value, "spare");
            return (Criteria) this;
        }

        public Criteria andSpareLessThanOrEqualTo(String value) {
            addCriterion("spare <=", value, "spare");
            return (Criteria) this;
        }

        public Criteria andSpareLike(String value) {
            addCriterion("spare like", value, "spare");
            return (Criteria) this;
        }

        public Criteria andSpareNotLike(String value) {
            addCriterion("spare not like", value, "spare");
            return (Criteria) this;
        }

        public Criteria andSpareIn(List<String> values) {
            addCriterion("spare in", values, "spare");
            return (Criteria) this;
        }

        public Criteria andSpareNotIn(List<String> values) {
            addCriterion("spare not in", values, "spare");
            return (Criteria) this;
        }

        public Criteria andSpareBetween(String value1, String value2) {
            addCriterion("spare between", value1, value2, "spare");
            return (Criteria) this;
        }

        public Criteria andSpareNotBetween(String value1, String value2) {
            addCriterion("spare not between", value1, value2, "spare");
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