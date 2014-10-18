package models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by maximiliano on 15/10/14.
 */
@Entity
@Table(name = "SESSIONS")
public class Session {
    @Expose
    private int id;
    @Expose
    private Timestamp creationDatetime;
    @Expose
    private Timestamp updatedDatetime;

    private List<SessionEntry> entries;

    public Session() {
        this.entries = new ArrayList<SessionEntry>();
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

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    public List<SessionEntry> getEntries() {
        return new ArrayList<SessionEntry>(entries);
    }

    private void setEntries(List<SessionEntry> entries) {
        this.entries = entries;
    }

    public void addEntry(SessionEntry entry) {
        this.entries.add(entry);
        entry.setSession(this);
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
