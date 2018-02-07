package com.payease.wallet.entity.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAccountTransactExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TAccountTransactExample() {
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

        public Criteria andTUserInfoIdIsNull() {
            addCriterion("t_user_info_id is null");
            return (Criteria) this;
        }

        public Criteria andTUserInfoIdIsNotNull() {
            addCriterion("t_user_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andTUserInfoIdEqualTo(Long value) {
            addCriterion("t_user_info_id =", value, "tUserInfoId");
            return (Criteria) this;
        }

        public Criteria andTUserInfoIdNotEqualTo(Long value) {
            addCriterion("t_user_info_id <>", value, "tUserInfoId");
            return (Criteria) this;
        }

        public Criteria andTUserInfoIdGreaterThan(Long value) {
            addCriterion("t_user_info_id >", value, "tUserInfoId");
            return (Criteria) this;
        }

        public Criteria andTUserInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("t_user_info_id >=", value, "tUserInfoId");
            return (Criteria) this;
        }

        public Criteria andTUserInfoIdLessThan(Long value) {
            addCriterion("t_user_info_id <", value, "tUserInfoId");
            return (Criteria) this;
        }

        public Criteria andTUserInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("t_user_info_id <=", value, "tUserInfoId");
            return (Criteria) this;
        }

        public Criteria andTUserInfoIdIn(List<Long> values) {
            addCriterion("t_user_info_id in", values, "tUserInfoId");
            return (Criteria) this;
        }

        public Criteria andTUserInfoIdNotIn(List<Long> values) {
            addCriterion("t_user_info_id not in", values, "tUserInfoId");
            return (Criteria) this;
        }

        public Criteria andTUserInfoIdBetween(Long value1, Long value2) {
            addCriterion("t_user_info_id between", value1, value2, "tUserInfoId");
            return (Criteria) this;
        }

        public Criteria andTUserInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("t_user_info_id not between", value1, value2, "tUserInfoId");
            return (Criteria) this;
        }

        public Criteria andTBankBaseIdIsNull() {
            addCriterion("t_bank_base_id is null");
            return (Criteria) this;
        }

        public Criteria andTBankBaseIdIsNotNull() {
            addCriterion("t_bank_base_id is not null");
            return (Criteria) this;
        }

        public Criteria andTBankBaseIdEqualTo(Long value) {
            addCriterion("t_bank_base_id =", value, "tBankBaseId");
            return (Criteria) this;
        }

        public Criteria andTBankBaseIdNotEqualTo(Long value) {
            addCriterion("t_bank_base_id <>", value, "tBankBaseId");
            return (Criteria) this;
        }

        public Criteria andTBankBaseIdGreaterThan(Long value) {
            addCriterion("t_bank_base_id >", value, "tBankBaseId");
            return (Criteria) this;
        }

        public Criteria andTBankBaseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("t_bank_base_id >=", value, "tBankBaseId");
            return (Criteria) this;
        }

        public Criteria andTBankBaseIdLessThan(Long value) {
            addCriterion("t_bank_base_id <", value, "tBankBaseId");
            return (Criteria) this;
        }

        public Criteria andTBankBaseIdLessThanOrEqualTo(Long value) {
            addCriterion("t_bank_base_id <=", value, "tBankBaseId");
            return (Criteria) this;
        }

        public Criteria andTBankBaseIdIn(List<Long> values) {
            addCriterion("t_bank_base_id in", values, "tBankBaseId");
            return (Criteria) this;
        }

        public Criteria andTBankBaseIdNotIn(List<Long> values) {
            addCriterion("t_bank_base_id not in", values, "tBankBaseId");
            return (Criteria) this;
        }

        public Criteria andTBankBaseIdBetween(Long value1, Long value2) {
            addCriterion("t_bank_base_id between", value1, value2, "tBankBaseId");
            return (Criteria) this;
        }

        public Criteria andTBankBaseIdNotBetween(Long value1, Long value2) {
            addCriterion("t_bank_base_id not between", value1, value2, "tBankBaseId");
            return (Criteria) this;
        }

        public Criteria andTransactNoIsNull() {
            addCriterion("transact_no is null");
            return (Criteria) this;
        }

        public Criteria andTransactNoIsNotNull() {
            addCriterion("transact_no is not null");
            return (Criteria) this;
        }

        public Criteria andTransactNoEqualTo(String value) {
            addCriterion("transact_no =", value, "transactNo");
            return (Criteria) this;
        }

        public Criteria andTransactNoNotEqualTo(String value) {
            addCriterion("transact_no <>", value, "transactNo");
            return (Criteria) this;
        }

        public Criteria andTransactNoGreaterThan(String value) {
            addCriterion("transact_no >", value, "transactNo");
            return (Criteria) this;
        }

        public Criteria andTransactNoGreaterThanOrEqualTo(String value) {
            addCriterion("transact_no >=", value, "transactNo");
            return (Criteria) this;
        }

        public Criteria andTransactNoLessThan(String value) {
            addCriterion("transact_no <", value, "transactNo");
            return (Criteria) this;
        }

        public Criteria andTransactNoLessThanOrEqualTo(String value) {
            addCriterion("transact_no <=", value, "transactNo");
            return (Criteria) this;
        }

        public Criteria andTransactNoLike(String value) {
            addCriterion("transact_no like", value, "transactNo");
            return (Criteria) this;
        }

        public Criteria andTransactNoNotLike(String value) {
            addCriterion("transact_no not like", value, "transactNo");
            return (Criteria) this;
        }

        public Criteria andTransactNoIn(List<String> values) {
            addCriterion("transact_no in", values, "transactNo");
            return (Criteria) this;
        }

        public Criteria andTransactNoNotIn(List<String> values) {
            addCriterion("transact_no not in", values, "transactNo");
            return (Criteria) this;
        }

        public Criteria andTransactNoBetween(String value1, String value2) {
            addCriterion("transact_no between", value1, value2, "transactNo");
            return (Criteria) this;
        }

        public Criteria andTransactNoNotBetween(String value1, String value2) {
            addCriterion("transact_no not between", value1, value2, "transactNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoIsNull() {
            addCriterion("bank_card_no is null");
            return (Criteria) this;
        }

        public Criteria andBankCardNoIsNotNull() {
            addCriterion("bank_card_no is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardNoEqualTo(String value) {
            addCriterion("bank_card_no =", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotEqualTo(String value) {
            addCriterion("bank_card_no <>", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoGreaterThan(String value) {
            addCriterion("bank_card_no >", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("bank_card_no >=", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoLessThan(String value) {
            addCriterion("bank_card_no <", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoLessThanOrEqualTo(String value) {
            addCriterion("bank_card_no <=", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoLike(String value) {
            addCriterion("bank_card_no like", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotLike(String value) {
            addCriterion("bank_card_no not like", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoIn(List<String> values) {
            addCriterion("bank_card_no in", values, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotIn(List<String> values) {
            addCriterion("bank_card_no not in", values, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoBetween(String value1, String value2) {
            addCriterion("bank_card_no between", value1, value2, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotBetween(String value1, String value2) {
            addCriterion("bank_card_no not between", value1, value2, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNull() {
            addCriterion("mobile_phone is null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNotNull() {
            addCriterion("mobile_phone is not null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneEqualTo(String value) {
            addCriterion("mobile_phone =", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotEqualTo(String value) {
            addCriterion("mobile_phone <>", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThan(String value) {
            addCriterion("mobile_phone >", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("mobile_phone >=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThan(String value) {
            addCriterion("mobile_phone <", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThanOrEqualTo(String value) {
            addCriterion("mobile_phone <=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLike(String value) {
            addCriterion("mobile_phone like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotLike(String value) {
            addCriterion("mobile_phone not like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIn(List<String> values) {
            addCriterion("mobile_phone in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotIn(List<String> values) {
            addCriterion("mobile_phone not in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneBetween(String value1, String value2) {
            addCriterion("mobile_phone between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotBetween(String value1, String value2) {
            addCriterion("mobile_phone not between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeIsNull() {
            addCriterion("bank_card_type is null");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeIsNotNull() {
            addCriterion("bank_card_type is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeEqualTo(String value) {
            addCriterion("bank_card_type =", value, "bankCardType");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeNotEqualTo(String value) {
            addCriterion("bank_card_type <>", value, "bankCardType");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeGreaterThan(String value) {
            addCriterion("bank_card_type >", value, "bankCardType");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeGreaterThanOrEqualTo(String value) {
            addCriterion("bank_card_type >=", value, "bankCardType");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeLessThan(String value) {
            addCriterion("bank_card_type <", value, "bankCardType");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeLessThanOrEqualTo(String value) {
            addCriterion("bank_card_type <=", value, "bankCardType");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeLike(String value) {
            addCriterion("bank_card_type like", value, "bankCardType");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeNotLike(String value) {
            addCriterion("bank_card_type not like", value, "bankCardType");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeIn(List<String> values) {
            addCriterion("bank_card_type in", values, "bankCardType");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeNotIn(List<String> values) {
            addCriterion("bank_card_type not in", values, "bankCardType");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeBetween(String value1, String value2) {
            addCriterion("bank_card_type between", value1, value2, "bankCardType");
            return (Criteria) this;
        }

        public Criteria andBankCardTypeNotBetween(String value1, String value2) {
            addCriterion("bank_card_type not between", value1, value2, "bankCardType");
            return (Criteria) this;
        }

        public Criteria andSingleLimitIsNull() {
            addCriterion("single_limit is null");
            return (Criteria) this;
        }

        public Criteria andSingleLimitIsNotNull() {
            addCriterion("single_limit is not null");
            return (Criteria) this;
        }

        public Criteria andSingleLimitEqualTo(String value) {
            addCriterion("single_limit =", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitNotEqualTo(String value) {
            addCriterion("single_limit <>", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitGreaterThan(String value) {
            addCriterion("single_limit >", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitGreaterThanOrEqualTo(String value) {
            addCriterion("single_limit >=", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitLessThan(String value) {
            addCriterion("single_limit <", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitLessThanOrEqualTo(String value) {
            addCriterion("single_limit <=", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitLike(String value) {
            addCriterion("single_limit like", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitNotLike(String value) {
            addCriterion("single_limit not like", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitIn(List<String> values) {
            addCriterion("single_limit in", values, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitNotIn(List<String> values) {
            addCriterion("single_limit not in", values, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitBetween(String value1, String value2) {
            addCriterion("single_limit between", value1, value2, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitNotBetween(String value1, String value2) {
            addCriterion("single_limit not between", value1, value2, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitIsNull() {
            addCriterion("day_limit is null");
            return (Criteria) this;
        }

        public Criteria andDayLimitIsNotNull() {
            addCriterion("day_limit is not null");
            return (Criteria) this;
        }

        public Criteria andDayLimitEqualTo(String value) {
            addCriterion("day_limit =", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitNotEqualTo(String value) {
            addCriterion("day_limit <>", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitGreaterThan(String value) {
            addCriterion("day_limit >", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitGreaterThanOrEqualTo(String value) {
            addCriterion("day_limit >=", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitLessThan(String value) {
            addCriterion("day_limit <", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitLessThanOrEqualTo(String value) {
            addCriterion("day_limit <=", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitLike(String value) {
            addCriterion("day_limit like", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitNotLike(String value) {
            addCriterion("day_limit not like", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitIn(List<String> values) {
            addCriterion("day_limit in", values, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitNotIn(List<String> values) {
            addCriterion("day_limit not in", values, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitBetween(String value1, String value2) {
            addCriterion("day_limit between", value1, value2, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitNotBetween(String value1, String value2) {
            addCriterion("day_limit not between", value1, value2, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("pay_type like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("pay_type not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeIsNull() {
            addCriterion("charge_type is null");
            return (Criteria) this;
        }

        public Criteria andChargeTypeIsNotNull() {
            addCriterion("charge_type is not null");
            return (Criteria) this;
        }

        public Criteria andChargeTypeEqualTo(String value) {
            addCriterion("charge_type =", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotEqualTo(String value) {
            addCriterion("charge_type <>", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeGreaterThan(String value) {
            addCriterion("charge_type >", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("charge_type >=", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeLessThan(String value) {
            addCriterion("charge_type <", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeLessThanOrEqualTo(String value) {
            addCriterion("charge_type <=", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeLike(String value) {
            addCriterion("charge_type like", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotLike(String value) {
            addCriterion("charge_type not like", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeIn(List<String> values) {
            addCriterion("charge_type in", values, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotIn(List<String> values) {
            addCriterion("charge_type not in", values, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeBetween(String value1, String value2) {
            addCriterion("charge_type between", value1, value2, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotBetween(String value1, String value2) {
            addCriterion("charge_type not between", value1, value2, "chargeType");
            return (Criteria) this;
        }

        public Criteria andBillAmountIsNull() {
            addCriterion("bill_amount is null");
            return (Criteria) this;
        }

        public Criteria andBillAmountIsNotNull() {
            addCriterion("bill_amount is not null");
            return (Criteria) this;
        }

        public Criteria andBillAmountEqualTo(String value) {
            addCriterion("bill_amount =", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountNotEqualTo(String value) {
            addCriterion("bill_amount <>", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountGreaterThan(String value) {
            addCriterion("bill_amount >", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountGreaterThanOrEqualTo(String value) {
            addCriterion("bill_amount >=", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountLessThan(String value) {
            addCriterion("bill_amount <", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountLessThanOrEqualTo(String value) {
            addCriterion("bill_amount <=", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountLike(String value) {
            addCriterion("bill_amount like", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountNotLike(String value) {
            addCriterion("bill_amount not like", value, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountIn(List<String> values) {
            addCriterion("bill_amount in", values, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountNotIn(List<String> values) {
            addCriterion("bill_amount not in", values, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountBetween(String value1, String value2) {
            addCriterion("bill_amount between", value1, value2, "billAmount");
            return (Criteria) this;
        }

        public Criteria andBillAmountNotBetween(String value1, String value2) {
            addCriterion("bill_amount not between", value1, value2, "billAmount");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountIsNull() {
            addCriterion("original_amount is null");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountIsNotNull() {
            addCriterion("original_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountEqualTo(String value) {
            addCriterion("original_amount =", value, "originalAmount");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountNotEqualTo(String value) {
            addCriterion("original_amount <>", value, "originalAmount");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountGreaterThan(String value) {
            addCriterion("original_amount >", value, "originalAmount");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountGreaterThanOrEqualTo(String value) {
            addCriterion("original_amount >=", value, "originalAmount");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountLessThan(String value) {
            addCriterion("original_amount <", value, "originalAmount");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountLessThanOrEqualTo(String value) {
            addCriterion("original_amount <=", value, "originalAmount");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountLike(String value) {
            addCriterion("original_amount like", value, "originalAmount");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountNotLike(String value) {
            addCriterion("original_amount not like", value, "originalAmount");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountIn(List<String> values) {
            addCriterion("original_amount in", values, "originalAmount");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountNotIn(List<String> values) {
            addCriterion("original_amount not in", values, "originalAmount");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountBetween(String value1, String value2) {
            addCriterion("original_amount between", value1, value2, "originalAmount");
            return (Criteria) this;
        }

        public Criteria andOriginalAmountNotBetween(String value1, String value2) {
            addCriterion("original_amount not between", value1, value2, "originalAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountIsNull() {
            addCriterion("sale_amount is null");
            return (Criteria) this;
        }

        public Criteria andSaleAmountIsNotNull() {
            addCriterion("sale_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSaleAmountEqualTo(String value) {
            addCriterion("sale_amount =", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotEqualTo(String value) {
            addCriterion("sale_amount <>", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountGreaterThan(String value) {
            addCriterion("sale_amount >", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountGreaterThanOrEqualTo(String value) {
            addCriterion("sale_amount >=", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountLessThan(String value) {
            addCriterion("sale_amount <", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountLessThanOrEqualTo(String value) {
            addCriterion("sale_amount <=", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountLike(String value) {
            addCriterion("sale_amount like", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotLike(String value) {
            addCriterion("sale_amount not like", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountIn(List<String> values) {
            addCriterion("sale_amount in", values, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotIn(List<String> values) {
            addCriterion("sale_amount not in", values, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountBetween(String value1, String value2) {
            addCriterion("sale_amount between", value1, value2, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotBetween(String value1, String value2) {
            addCriterion("sale_amount not between", value1, value2, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIsNull() {
            addCriterion("goods_info is null");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIsNotNull() {
            addCriterion("goods_info is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoEqualTo(String value) {
            addCriterion("goods_info =", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoNotEqualTo(String value) {
            addCriterion("goods_info <>", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoGreaterThan(String value) {
            addCriterion("goods_info >", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoGreaterThanOrEqualTo(String value) {
            addCriterion("goods_info >=", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoLessThan(String value) {
            addCriterion("goods_info <", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoLessThanOrEqualTo(String value) {
            addCriterion("goods_info <=", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoLike(String value) {
            addCriterion("goods_info like", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoNotLike(String value) {
            addCriterion("goods_info not like", value, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoIn(List<String> values) {
            addCriterion("goods_info in", values, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoNotIn(List<String> values) {
            addCriterion("goods_info not in", values, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoBetween(String value1, String value2) {
            addCriterion("goods_info between", value1, value2, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andGoodsInfoNotBetween(String value1, String value2) {
            addCriterion("goods_info not between", value1, value2, "goodsInfo");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIsNull() {
            addCriterion("merchant_name is null");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIsNotNull() {
            addCriterion("merchant_name is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantNameEqualTo(String value) {
            addCriterion("merchant_name =", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotEqualTo(String value) {
            addCriterion("merchant_name <>", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameGreaterThan(String value) {
            addCriterion("merchant_name >", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_name >=", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLessThan(String value) {
            addCriterion("merchant_name <", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLessThanOrEqualTo(String value) {
            addCriterion("merchant_name <=", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLike(String value) {
            addCriterion("merchant_name like", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotLike(String value) {
            addCriterion("merchant_name not like", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIn(List<String> values) {
            addCriterion("merchant_name in", values, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotIn(List<String> values) {
            addCriterion("merchant_name not in", values, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameBetween(String value1, String value2) {
            addCriterion("merchant_name between", value1, value2, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotBetween(String value1, String value2) {
            addCriterion("merchant_name not between", value1, value2, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNoIsNull() {
            addCriterion("merchant_no is null");
            return (Criteria) this;
        }

        public Criteria andMerchantNoIsNotNull() {
            addCriterion("merchant_no is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantNoEqualTo(String value) {
            addCriterion("merchant_no =", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoNotEqualTo(String value) {
            addCriterion("merchant_no <>", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoGreaterThan(String value) {
            addCriterion("merchant_no >", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_no >=", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoLessThan(String value) {
            addCriterion("merchant_no <", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoLessThanOrEqualTo(String value) {
            addCriterion("merchant_no <=", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoLike(String value) {
            addCriterion("merchant_no like", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoNotLike(String value) {
            addCriterion("merchant_no not like", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoIn(List<String> values) {
            addCriterion("merchant_no in", values, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoNotIn(List<String> values) {
            addCriterion("merchant_no not in", values, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoBetween(String value1, String value2) {
            addCriterion("merchant_no between", value1, value2, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoNotBetween(String value1, String value2) {
            addCriterion("merchant_no not between", value1, value2, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andOrdertimeIsNull() {
            addCriterion("ordertime is null");
            return (Criteria) this;
        }

        public Criteria andOrdertimeIsNotNull() {
            addCriterion("ordertime is not null");
            return (Criteria) this;
        }

        public Criteria andOrdertimeEqualTo(Date value) {
            addCriterion("ordertime =", value, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeNotEqualTo(Date value) {
            addCriterion("ordertime <>", value, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeGreaterThan(Date value) {
            addCriterion("ordertime >", value, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ordertime >=", value, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeLessThan(Date value) {
            addCriterion("ordertime <", value, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeLessThanOrEqualTo(Date value) {
            addCriterion("ordertime <=", value, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeIn(List<Date> values) {
            addCriterion("ordertime in", values, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeNotIn(List<Date> values) {
            addCriterion("ordertime not in", values, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeBetween(Date value1, Date value2) {
            addCriterion("ordertime between", value1, value2, "ordertime");
            return (Criteria) this;
        }

        public Criteria andOrdertimeNotBetween(Date value1, Date value2) {
            addCriterion("ordertime not between", value1, value2, "ordertime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andDealStatusIsNull() {
            addCriterion("deal_status is null");
            return (Criteria) this;
        }

        public Criteria andDealStatusIsNotNull() {
            addCriterion("deal_status is not null");
            return (Criteria) this;
        }

        public Criteria andDealStatusEqualTo(String value) {
            addCriterion("deal_status =", value, "dealStatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusNotEqualTo(String value) {
            addCriterion("deal_status <>", value, "dealStatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusGreaterThan(String value) {
            addCriterion("deal_status >", value, "dealStatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusGreaterThanOrEqualTo(String value) {
            addCriterion("deal_status >=", value, "dealStatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusLessThan(String value) {
            addCriterion("deal_status <", value, "dealStatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusLessThanOrEqualTo(String value) {
            addCriterion("deal_status <=", value, "dealStatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusLike(String value) {
            addCriterion("deal_status like", value, "dealStatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusNotLike(String value) {
            addCriterion("deal_status not like", value, "dealStatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusIn(List<String> values) {
            addCriterion("deal_status in", values, "dealStatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusNotIn(List<String> values) {
            addCriterion("deal_status not in", values, "dealStatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusBetween(String value1, String value2) {
            addCriterion("deal_status between", value1, value2, "dealStatus");
            return (Criteria) this;
        }

        public Criteria andDealStatusNotBetween(String value1, String value2) {
            addCriterion("deal_status not between", value1, value2, "dealStatus");
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