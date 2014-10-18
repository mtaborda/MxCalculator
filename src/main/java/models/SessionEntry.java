package models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by maximiliano on 15/10/14.
 */
@Entity
@Table(name = "SESSION_ENTRIES")
public class SessionEntry {
    private int id;
    private Timestamp creationDatetime;
    private Timestamp updatedDatetime;
    private String expression;
    private String result;
    private Session session;

    public SessionEntry() {}

    public SessionEntry(Calculation calculation) {
        this.expression = calculation.getExpression();
        this.result = calculation.getResult();
    }

    public SessionEntry(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CREATION_DATETIME")
    public Timestamp getCreationDatetime() {
        return creationDatetime;
    }

    private void setCreationDatetime(Timestamp creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    @Basic
    @Column(name = "UPDATED_DATETIME")
    public Timestamp getUpdatedDatetime() {
        return updatedDatetime;
    }

    private void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    @ManyToOne
    @JoinColumn(name = "SESSION")
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Basic
    @Column(name = "EXPRESSION")
    public String getExpression() {
        return expression;
    }

    private void setExpression(String expression) {
        this.expression = expression;
    }

    @Basic
    @JoinColumn(name = "RESULT")
    public String getResult() {
        return result;
    }

    private void setResult(String result) {
        this.result = result;
    }

    public Calculation calculation() {
        return new Calculation(expression, result);
    }

    @PrePersist
    protected void onCreate() {
        creationDatetime = currentTimestamp();
        updatedDatetime = currentTimestamp();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDatetime = currentTimestamp();
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(Calendar.getInstance().getTime().getTime());
    }
}
