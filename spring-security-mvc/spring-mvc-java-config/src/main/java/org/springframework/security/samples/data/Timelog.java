package org.springframework.security.samples.data;

/**
 * @author wvergara, created on 6/9/15.
 */

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * @author katsi02
 */
@Entity
@Table(name = "USER_TIMELOG")
public class Timelog extends AbstractPersistable<Long> {

    private Date inTime;
    private Date outTime;


    @ManyToOne( cascade = CascadeType.REFRESH)
    private User user;

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}