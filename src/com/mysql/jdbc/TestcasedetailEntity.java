package com.mysql.jdbc;

import javax.persistence.*;

/**
 * Created by s016374 on 15/6/30.
 */
@Entity
@Table(name = "testcasedetail", schema = "", catalog = "autotest")
public class TestcasedetailEntity {
    private int id;
    private String name;
    private Integer testCaseId;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "testCase_id")
    public Integer getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Integer testCaseId) {
        this.testCaseId = testCaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestcasedetailEntity that = (TestcasedetailEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (testCaseId != null ? !testCaseId.equals(that.testCaseId) : that.testCaseId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (testCaseId != null ? testCaseId.hashCode() : 0);
        return result;
    }
}
