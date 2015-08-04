package com.dandy.core;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "roleId")
    @GeneratedValue
    private int roleId;
    @Column(name = "pos")
    private String pos;

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public void setPos(String pos) {
        this.pos = pos;
    }
    public int getRoleId() {
        return this.roleId;
    }
    public String getPos() {
        return this.pos;
    }
}
