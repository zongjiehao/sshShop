package com.haozj.shop.category.adminaction;

import java.util.List;

import com.haozj.shop.category.service.CategoryService;
import com.haozj.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台一级分类管理的action
 * @author haozj
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
    private Category category = new Category();
    private CategoryService categoryService;
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Override
    public Category getModel() {
        return category;
    }
    public String findAll(){
        List<Category> cList = categoryService.findAll();
        ActionContext.getContext().getValueStack().set("cList", cList);
        return "findAll";
    }
    public String save(){
        categoryService.save(category);
        return "saveSuccess";
    }
    public String delete(){
        category = categoryService.findByCid(category.getCid());
        categoryService.delete(category);
            return "deleteSuccess";
    }
    public String edit(){
        category = categoryService.findByCid(category.getCid());
        return "editSuccess";
    }
    public String update(){
        categoryService.update(category);
        return "updateSuccess";
    }
}
