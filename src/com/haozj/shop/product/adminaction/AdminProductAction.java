package com.haozj.shop.product.adminaction;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.haozj.shop.categorysecond.service.CategorySecondService;
import com.haozj.shop.categorysecond.vo.CategorySecond;
import com.haozj.shop.product.service.ProductService;
import com.haozj.shop.product.vo.Product;
import com.haozj.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
    private Product product = new Product();
    private ProductService productService;
    private CategorySecondService categorySecondService;
    private int page;
    private File upload;//上传的文件
    private String uploadFileName;// 上传的文件名
    private String uploadContextType;//上传的文件类型
    
    public void setUpload(File upload) {
        this.upload = upload;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
    public void setUploadContextType(String uploadContextType) {
        this.uploadContextType = uploadContextType;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    @Override
    public Product getModel() {
        return product;
    }
    public String findAll(){
        PageBean<Product> pageBean =  productService.findByPage(page);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return "findAll";
    }
    public String addPage(){
        List<CategorySecond> csList =  categorySecondService.findAll();
        ActionContext.getContext().getValueStack().set("csList", csList);
        return "addPage";
    }
    //商品的添加
    public String save() throws IOException{
        product.setPdate(new Date());
        if (upload!=null) {
            //获得文件上传的绝对路径
            //String realPath = ServletActionContext.getServletContext().getRealPath("/products");
            String realPath = "D:\\img\\products";
            File diskFile = new File(realPath+"\\"+uploadFileName);
            FileUtils.copyFile(upload, diskFile);
            product.setImage("products/"+uploadFileName);
        }
        productService.save(product);
        return "saveSuccess";
    }
    //删除商品的上传图片
    public String delete(){
        product = productService.findByPid(product.getPid());
        //删除上传的图片
        String path = product.getImage();
        if (path!=null) {
            String realPath = "D:\\img\\"+path;
            //System.out.println("物理路径为："+realPath);
            File file = new File(realPath);
            file.delete();
        }        
        productService.delete(product);
        return "deleteSuccess";
    }
    //跳转到编辑页面
    public String edit(){
        product = productService.findByPid(product.getPid());
        List<CategorySecond> csList = categorySecondService.findAll();
        ActionContext.getContext().getValueStack().set("csList", csList);
        return "editSuccess";
    }
    //修改商品属性
    public String update() throws IOException{
        product.setPdate(new Date());
        //文件上传
        if (upload!=null) {
            //删除旧的文件
            String path = product.getImage();
            String oldPath = "D:\\img\\";
            //获得文件上传的绝对路径
            String realPath = "D:\\img\\products";;
            //创建文件
            //System.out.println("绝对路径:"+realPath);
            File oldFile = new File(oldPath+path);
            if (oldFile!=null) {
                oldFile.delete();
            }
            File diskFile = new File(realPath+"\\"+uploadFileName);
            FileUtils.copyFile(upload, diskFile);
            product.setImage("products/"+uploadFileName);
        }
        productService.update(product);
        return "updateSuccess";
    }

}
