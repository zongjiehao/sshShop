package com.haozj.shop.categorysecond.adminaction;

import java.util.List;

import com.haozj.shop.category.service.CategoryService;
import com.haozj.shop.category.vo.Category;
import com.haozj.shop.categorysecond.service.CategorySecondService;
import com.haozj.shop.categorysecond.vo.CategorySecond;
import com.haozj.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction  extends ActionSupport implements ModelDriven<CategorySecond> {
    private CategoryService categoryService;
    private CategorySecondService categorySecondService;
    private CategorySecond categorySecond = new CategorySecond();
    private Integer page;
    public void setPage(Integer page) {
        this.page = page;
    }
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Override
    public CategorySecond getModel() {
        return categorySecond;
    }
    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }
    //分页查询二级分类
    public String findAll(){
        PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return "findAll";
    }
    //跳转到添加二级分类的页面
    public String addPage(){    
        List<Category> cList = categoryService.findAll();
        ActionContext.getContext().getValueStack().set("cList", cList);
        return "addSuccess";
    }
    //保存添加结果
    public String save(){
        categorySecondService.save(categorySecond);
        return "saveSuccess";
    }
    //删除二级分类
    public String delete(){
        categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
        categorySecondService.delete(categorySecond);
        return "deleteSuccess";
    }
    //编辑二级分类
    public String edit(){
        categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
        List<Category> cList = categoryService.findAll();
        ActionContext.getContext().getValueStack().set("cList", cList);
        return "editSuccess";
    }
    public String update(){
        categorySecondService.update(categorySecond);
        return "updateSuccess";
    }

}
