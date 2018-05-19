package bkr.api.session.dto;

import lombok.Data;

/**
 * 角色dto
 * 
 * @author chengd
 */
@Data
public class RoleDto {
    /** 角色Id */
    private Long roleId;

    /** 角色名 */
    private String name;

    /** 说明 */
    private String memo;
}
