/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author byron
 */
@Entity
@Table(name = "groupup_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupupGroup.findAll", query = "SELECT g FROM GroupupGroup g"),
    @NamedQuery(name = "GroupupGroup.findById", query = "SELECT g FROM GroupupGroup g WHERE g.id = :id"),
    @NamedQuery(name = "GroupupGroup.findByName", query = "SELECT g FROM GroupupGroup g WHERE g.name = :name")})
public class GroupupGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "groupup_user_group", joinColumns = {
        @JoinColumn(name = "group_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<GroupupUser> groupupUserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
    private Collection<GroupupTimeslot> groupupTimeslotCollection;

    public GroupupGroup() {
    }

    public GroupupGroup(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<GroupupUser> getGroupupUserCollection() {
        return groupupUserCollection;
    }

    public void setGroupupUserCollection(Collection<GroupupUser> groupupUserCollection) {
        this.groupupUserCollection = groupupUserCollection;
    }

    @XmlTransient
    public Collection<GroupupTimeslot> getGroupupTimeslotCollection() {
        return groupupTimeslotCollection;
    }

    public void setGroupupTimeslotCollection(Collection<GroupupTimeslot> groupupTimeslotCollection) {
        this.groupupTimeslotCollection = groupupTimeslotCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupupGroup)) {
            return false;
        }
        GroupupGroup other = (GroupupGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.GroupupGroup[ id=" + id + " ]";
    }
    
}
