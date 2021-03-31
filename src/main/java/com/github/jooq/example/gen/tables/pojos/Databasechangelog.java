/*
 * This file is generated by jOOQ.
 */
package com.github.jooq.example.gen.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Databasechangelog implements Serializable {

    private static final long serialVersionUID = -1266884735;

    private String        id;
    private String        author;
    private String        filename;
    private LocalDateTime dateexecuted;
    private Integer       orderexecuted;
    private String        exectype;
    private String        md5sum;
    private String        description;
    private String        comments;
    private String        tag;
    private String        liquibase;
    private String        contexts;
    private String        labels;

    public Databasechangelog() {}

    public Databasechangelog(Databasechangelog value) {
        this.id = value.id;
        this.author = value.author;
        this.filename = value.filename;
        this.dateexecuted = value.dateexecuted;
        this.orderexecuted = value.orderexecuted;
        this.exectype = value.exectype;
        this.md5sum = value.md5sum;
        this.description = value.description;
        this.comments = value.comments;
        this.tag = value.tag;
        this.liquibase = value.liquibase;
        this.contexts = value.contexts;
        this.labels = value.labels;
    }

    public Databasechangelog(
        String        id,
        String        author,
        String        filename,
        LocalDateTime dateexecuted,
        Integer       orderexecuted,
        String        exectype,
        String        md5sum,
        String        description,
        String        comments,
        String        tag,
        String        liquibase,
        String        contexts,
        String        labels
    ) {
        this.id = id;
        this.author = author;
        this.filename = filename;
        this.dateexecuted = dateexecuted;
        this.orderexecuted = orderexecuted;
        this.exectype = exectype;
        this.md5sum = md5sum;
        this.description = description;
        this.comments = comments;
        this.tag = tag;
        this.liquibase = liquibase;
        this.contexts = contexts;
        this.labels = labels;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LocalDateTime getDateexecuted() {
        return this.dateexecuted;
    }

    public void setDateexecuted(LocalDateTime dateexecuted) {
        this.dateexecuted = dateexecuted;
    }

    public Integer getOrderexecuted() {
        return this.orderexecuted;
    }

    public void setOrderexecuted(Integer orderexecuted) {
        this.orderexecuted = orderexecuted;
    }

    public String getExectype() {
        return this.exectype;
    }

    public void setExectype(String exectype) {
        this.exectype = exectype;
    }

    public String getMd5sum() {
        return this.md5sum;
    }

    public void setMd5sum(String md5sum) {
        this.md5sum = md5sum;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLiquibase() {
        return this.liquibase;
    }

    public void setLiquibase(String liquibase) {
        this.liquibase = liquibase;
    }

    public String getContexts() {
        return this.contexts;
    }

    public void setContexts(String contexts) {
        this.contexts = contexts;
    }

    public String getLabels() {
        return this.labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Databasechangelog (");

        sb.append(id);
        sb.append(", ").append(author);
        sb.append(", ").append(filename);
        sb.append(", ").append(dateexecuted);
        sb.append(", ").append(orderexecuted);
        sb.append(", ").append(exectype);
        sb.append(", ").append(md5sum);
        sb.append(", ").append(description);
        sb.append(", ").append(comments);
        sb.append(", ").append(tag);
        sb.append(", ").append(liquibase);
        sb.append(", ").append(contexts);
        sb.append(", ").append(labels);

        sb.append(")");
        return sb.toString();
    }
}
