package bkr.core.user.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * 角色表 model
 * 
 * @author chengd
 */
@Entity
@Table(name = "role")
@Data
public class Role {
    /** 角色Id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;

    /** 角色名 */
    private String name;

    /** 说明 */
    private String memo;

    /** 角色用户 */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "role")
    private List<User> userList;
}
