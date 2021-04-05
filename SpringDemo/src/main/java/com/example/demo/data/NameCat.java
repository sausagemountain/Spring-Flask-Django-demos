package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

public class NameCat implements Serializable, Persistable<String> {
    @Id
    private String id = null;

    private String name = null;

    private String cat = null;

    @ReadOnlyProperty
    @Transient
    private Boolean isNew = true;

    public NameCat() {
    }

    public NameCat(String id, String name, String cat) {
        this.id = id;
        this.name = name;
        this.cat = cat;
    }

    public String getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return isNew;
    }

    public void setId(String id) {
        if (this.id != null) {
            this.isNew = false;
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (this.name != null) {
            this.isNew = false;
        }
        this.name = name;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        if (this.cat != null) {
            this.isNew = false;
        }
        this.cat = cat;
    }
}
