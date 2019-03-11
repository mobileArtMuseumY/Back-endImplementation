package com.artmall.DO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorksAttachmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorksAttachmentExample() {
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

        public Criteria andAttachmentNameIsNull() {
            addCriterion("attachment_name is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameIsNotNull() {
            addCriterion("attachment_name is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameEqualTo(String value) {
            addCriterion("attachment_name =", value, "attachmentName");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameNotEqualTo(String value) {
            addCriterion("attachment_name <>", value, "attachmentName");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameGreaterThan(String value) {
            addCriterion("attachment_name >", value, "attachmentName");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("attachment_name >=", value, "attachmentName");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameLessThan(String value) {
            addCriterion("attachment_name <", value, "attachmentName");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameLessThanOrEqualTo(String value) {
            addCriterion("attachment_name <=", value, "attachmentName");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameLike(String value) {
            addCriterion("attachment_name like", value, "attachmentName");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameNotLike(String value) {
            addCriterion("attachment_name not like", value, "attachmentName");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameIn(List<String> values) {
            addCriterion("attachment_name in", values, "attachmentName");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameNotIn(List<String> values) {
            addCriterion("attachment_name not in", values, "attachmentName");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameBetween(String value1, String value2) {
            addCriterion("attachment_name between", value1, value2, "attachmentName");
            return (Criteria) this;
        }

        public Criteria andAttachmentNameNotBetween(String value1, String value2) {
            addCriterion("attachment_name not between", value1, value2, "attachmentName");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathIsNull() {
            addCriterion("attachment_path is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathIsNotNull() {
            addCriterion("attachment_path is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathEqualTo(String value) {
            addCriterion("attachment_path =", value, "attachmentPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathNotEqualTo(String value) {
            addCriterion("attachment_path <>", value, "attachmentPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathGreaterThan(String value) {
            addCriterion("attachment_path >", value, "attachmentPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathGreaterThanOrEqualTo(String value) {
            addCriterion("attachment_path >=", value, "attachmentPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathLessThan(String value) {
            addCriterion("attachment_path <", value, "attachmentPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathLessThanOrEqualTo(String value) {
            addCriterion("attachment_path <=", value, "attachmentPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathLike(String value) {
            addCriterion("attachment_path like", value, "attachmentPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathNotLike(String value) {
            addCriterion("attachment_path not like", value, "attachmentPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathIn(List<String> values) {
            addCriterion("attachment_path in", values, "attachmentPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathNotIn(List<String> values) {
            addCriterion("attachment_path not in", values, "attachmentPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathBetween(String value1, String value2) {
            addCriterion("attachment_path between", value1, value2, "attachmentPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentPathNotBetween(String value1, String value2) {
            addCriterion("attachment_path not between", value1, value2, "attachmentPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentSizeIsNull() {
            addCriterion("attachment_size is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentSizeIsNotNull() {
            addCriterion("attachment_size is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentSizeEqualTo(Long value) {
            addCriterion("attachment_size =", value, "attachmentSize");
            return (Criteria) this;
        }

        public Criteria andAttachmentSizeNotEqualTo(Long value) {
            addCriterion("attachment_size <>", value, "attachmentSize");
            return (Criteria) this;
        }

        public Criteria andAttachmentSizeGreaterThan(Long value) {
            addCriterion("attachment_size >", value, "attachmentSize");
            return (Criteria) this;
        }

        public Criteria andAttachmentSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("attachment_size >=", value, "attachmentSize");
            return (Criteria) this;
        }

        public Criteria andAttachmentSizeLessThan(Long value) {
            addCriterion("attachment_size <", value, "attachmentSize");
            return (Criteria) this;
        }

        public Criteria andAttachmentSizeLessThanOrEqualTo(Long value) {
            addCriterion("attachment_size <=", value, "attachmentSize");
            return (Criteria) this;
        }

        public Criteria andAttachmentSizeIn(List<Long> values) {
            addCriterion("attachment_size in", values, "attachmentSize");
            return (Criteria) this;
        }

        public Criteria andAttachmentSizeNotIn(List<Long> values) {
            addCriterion("attachment_size not in", values, "attachmentSize");
            return (Criteria) this;
        }

        public Criteria andAttachmentSizeBetween(Long value1, Long value2) {
            addCriterion("attachment_size between", value1, value2, "attachmentSize");
            return (Criteria) this;
        }

        public Criteria andAttachmentSizeNotBetween(Long value1, Long value2) {
            addCriterion("attachment_size not between", value1, value2, "attachmentSize");
            return (Criteria) this;
        }

        public Criteria andWorksIdIsNull() {
            addCriterion("works_id is null");
            return (Criteria) this;
        }

        public Criteria andWorksIdIsNotNull() {
            addCriterion("works_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorksIdEqualTo(Long value) {
            addCriterion("works_id =", value, "worksId");
            return (Criteria) this;
        }

        public Criteria andWorksIdNotEqualTo(Long value) {
            addCriterion("works_id <>", value, "worksId");
            return (Criteria) this;
        }

        public Criteria andWorksIdGreaterThan(Long value) {
            addCriterion("works_id >", value, "worksId");
            return (Criteria) this;
        }

        public Criteria andWorksIdGreaterThanOrEqualTo(Long value) {
            addCriterion("works_id >=", value, "worksId");
            return (Criteria) this;
        }

        public Criteria andWorksIdLessThan(Long value) {
            addCriterion("works_id <", value, "worksId");
            return (Criteria) this;
        }

        public Criteria andWorksIdLessThanOrEqualTo(Long value) {
            addCriterion("works_id <=", value, "worksId");
            return (Criteria) this;
        }

        public Criteria andWorksIdIn(List<Long> values) {
            addCriterion("works_id in", values, "worksId");
            return (Criteria) this;
        }

        public Criteria andWorksIdNotIn(List<Long> values) {
            addCriterion("works_id not in", values, "worksId");
            return (Criteria) this;
        }

        public Criteria andWorksIdBetween(Long value1, Long value2) {
            addCriterion("works_id between", value1, value2, "worksId");
            return (Criteria) this;
        }

        public Criteria andWorksIdNotBetween(Long value1, Long value2) {
            addCriterion("works_id not between", value1, value2, "worksId");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathIsNull() {
            addCriterion("attachment_watermark_path is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathIsNotNull() {
            addCriterion("attachment_watermark_path is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathEqualTo(String value) {
            addCriterion("attachment_watermark_path =", value, "attachmentWatermarkPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathNotEqualTo(String value) {
            addCriterion("attachment_watermark_path <>", value, "attachmentWatermarkPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathGreaterThan(String value) {
            addCriterion("attachment_watermark_path >", value, "attachmentWatermarkPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathGreaterThanOrEqualTo(String value) {
            addCriterion("attachment_watermark_path >=", value, "attachmentWatermarkPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathLessThan(String value) {
            addCriterion("attachment_watermark_path <", value, "attachmentWatermarkPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathLessThanOrEqualTo(String value) {
            addCriterion("attachment_watermark_path <=", value, "attachmentWatermarkPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathLike(String value) {
            addCriterion("attachment_watermark_path like", value, "attachmentWatermarkPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathNotLike(String value) {
            addCriterion("attachment_watermark_path not like", value, "attachmentWatermarkPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathIn(List<String> values) {
            addCriterion("attachment_watermark_path in", values, "attachmentWatermarkPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathNotIn(List<String> values) {
            addCriterion("attachment_watermark_path not in", values, "attachmentWatermarkPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathBetween(String value1, String value2) {
            addCriterion("attachment_watermark_path between", value1, value2, "attachmentWatermarkPath");
            return (Criteria) this;
        }

        public Criteria andAttachmentWatermarkPathNotBetween(String value1, String value2) {
            addCriterion("attachment_watermark_path not between", value1, value2, "attachmentWatermarkPath");
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