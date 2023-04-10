package com.yxs.controller;

import com.yxs.domain.ResponseResult;
import com.yxs.domain.dto.ChangeRoleStatusDto;
import com.yxs.domain.entity.Role;
import com.yxs.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: RoleCotroller
 * @Desription:
 * @date 2023/4/10 9:26
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult listRole(Role role, Integer pageNum, Integer pageSize) {
        return roleService.selectRolePage(role, pageNum, pageSize);
    }

    @GetMapping("/listAllRole")
    public ResponseResult listAllRole() {
        return ResponseResult.okResult(roleService.selectRoleAll());
    }

    @GetMapping(value = "/{roleId}")
    public ResponseResult getInfoRole(@PathVariable Long roleId) {
        return ResponseResult.okResult(roleService.getById(roleId));
    }

    @PostMapping
    public ResponseResult addRole(@RequestBody Role role) {

        roleService.insertRole(role);
        return ResponseResult.okResult();

    }

    @PutMapping
    public ResponseResult editRole(@RequestBody Role role) {

        roleService.updateRole(role);
        return ResponseResult.okResult();

    }

    @DeleteMapping("/{id}")
    public ResponseResult removeRole(@PathVariable("id") Long id) {

        roleService.removeById(id);
        return ResponseResult.okResult();

    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody ChangeRoleStatusDto roleStatusDto) {

        Role role = new Role();
        role.setId(roleStatusDto.getRoleId()); role.setStatus(roleStatusDto.getStatus());
        return ResponseResult.okResult(roleService.updateById(role));

    }

}
