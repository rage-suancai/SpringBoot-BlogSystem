package com.yxs.controller;

import com.yxs.domain.ResponseResult;
import com.yxs.domain.entity.Menu;
import com.yxs.domain.vo.MenuTreeVo;
import com.yxs.domain.vo.MenuVo;
import com.yxs.domain.vo.RoleMenuTreeSelectVo;
import com.yxs.service.MenuService;
import com.yxs.utils.BeanCopyUtils;
import com.yxs.utils.SystemConverter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YXS
 * @PackageName: com.yxs.controller
 * @ClassName: MenuController
 * @Desription:
 * @date 2023/4/8 15:47
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/treeselect")
    public ResponseResult treeselect() {

        List<Menu> menus = menuService.selectMenuList(new Menu());
        List<MenuTreeVo> options = SystemConverter.buildMenuSelectTree(menus);
        return ResponseResult.okResult(options);

    }

    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public ResponseResult roleMenuTreeSelect(@PathVariable("roleId") Long roleId) {

        List<Menu> menus = menuService.selectMenuList(new Menu());
        List<Long> checkedKeys = menuService.selectMenuListByRoleId(roleId);
        List<MenuTreeVo> menuTreeVos = SystemConverter.buildMenuSelectTree(menus);
        RoleMenuTreeSelectVo vo = new RoleMenuTreeSelectVo(checkedKeys, menuTreeVos);
        return ResponseResult.okResult(vo);

    }

    @GetMapping("/list")
    public ResponseResult menuList(Menu menu) {

        List<Menu> menus = menuService.selectMenuList(menu);
        List<MenuVo> menuVos = BeanCopyUtils.copyBeanList(menus, MenuVo.class);
        return ResponseResult.okResult(menuVos);

    }

    @PostMapping("/{id}")
    public ResponseResult addMenu(@RequestBody Menu menu) {

        menuService.save(menu);
        return ResponseResult.okResult();

    }

    @GetMapping(value = "/{menuId}")
    public ResponseResult getInfoMenu(@PathVariable Long menuId) {
        return ResponseResult.okResult(menuService.getById(menuId));
    }

    @PutMapping
    public ResponseResult editMenu(@RequestBody Menu menu) {

        if (menu.getId().equals(menu.getParentId()))
            return ResponseResult.errorResult(500, "修改菜单'" + menu.getMenuName() + "'失败, 上级菜单不能选择自己");
        menuService.updateById(menu);
        return ResponseResult.okResult();

    }

    @DeleteMapping(value = "/{menuId}")
    public ResponseResult removeMenu(@PathVariable("menuId") Long menuId) {

        if (menuService.hashChild(menuId)) return ResponseResult.errorResult(500, "存在子菜单不允许删除");
        menuService.removeById(menuId);
        return ResponseResult.okResult();

    }

}
