package com.sunrise.common;

import com.alibaba.fastjson.JSONObject;
import com.sunrise.framework.shiro.ShiroUser;
import com.sunrise.model.User;
import com.sunrise.service.UserService;
import com.zhangzlyuyx.fastssm.common.Return;
import com.zhangzlyuyx.fastssm.mybatis.PageCondition;
import com.zhangzlyuyx.fastssm.mybatis.PageQuery;
import com.zhangzlyuyx.fastssm.mybatis.PageResult;
import com.zhangzlyuyx.fastssm.util.ControllerUtils;
import com.zhangzlyuyx.fastssm.util.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

public abstract class BaseController<T> {
	@Autowired
	private UserService userService;
	/**
	 * 初始化
	 * @param binder
	 */
	@InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
		this.beforeInitBinder(binder);
		
		//自动转换日期类型的字段格式
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
        	@Override
        	public void setAsText(String text) throws IllegalArgumentException {
        		if(StringUtils.isEmpty(text)) {
        			super.setValue(null);
        		} else {
        			Date value = DateUtils.parse(text);
        			super.setValue(value);
        		}
        	}
        	
        	@Override
        	public String getAsText() {
        		Object value = super.getValue();
        		if(value == null) {
        			return "";
        		}
        		Date date = (Date)value;
        		return DateUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        	}
        });

        //防止XSS攻击
        /*
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
        	@Override
        	public void setAsText(String text) throws IllegalArgumentException {
        		if(text == null) {
        			super.setValue(null);
        		}
        		super.setAsText(text);
        	}
        	
        	@Override
        	public String getAsText() {
        		Object value = this.getValue();
        		return value != null ? "": value.toString();
        	}
        });
        */

        //判断请求的目标对象中的属性类型是否为枚举实例，如果是加入到映射转换中
        /*
        if(binder.getTarget() == null){
        	return;
        }
        Field[] fields = binder.getTarget().getClass().getDeclaredFields();
        for(final Field field : fields) {
			if(BaseEnum.class.isAssignableFrom(field.getType())){
				binder.registerCustomEditor(field.getType(), new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) throws IllegalArgumentException {
						if(text == null) {
							super.setValue(null);
						}else {
							Class<? extends BaseEnum<?>> fieldType = (Class<? extends BaseEnum<?>>) field.getType();
							super.setValue(fieldType.getEnumConstants()[0].getInstanceByKey(text));
						}
					}
				});
			}
		}
        */
		this.afterInitBinder(binder);
	}
	
	/**
	 * 初始化预处理
	 * @param binder
	 */
	protected void beforeInitBinder(ServletRequestDataBinder binder) {
		
	}
	
	/**
	 * 初始化后处理
	 * @param binder
	 */
	protected void afterInitBinder(ServletRequestDataBinder binder) {
		
	}
	
	public abstract com.sunrise.common.BaseService<T> getTargetService();
	
	/**
	 * 获取类型
	 * @return
	 */
	public Class<T> getTargetType(){
		Class<T> classz = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return classz;
	}
	
	/**
	 * 获取类型名称
	 * @return
	 */
	public String getTargetTypeName() {
		Class<T> classz = this.getTargetType();
		String className = classz.getName();
		className = className.substring(0, 1).toLowerCase() + className.substring(1);
		return className;
	}
	
	/**
	 * 返回失败消息
	 * @param errorMsg
	 * @return
	 */
	public Object renderFail(String errorMsg) {
		return ControllerUtils.returnFail(errorMsg);
	}
	
	/**
	 * 返回成功信息
	 * @param obj
	 * @param msg
	 * @return
	 */
	public Object renderSuccess(Object obj, String msg) {
		return ControllerUtils.returnSuccess(obj, msg);
	}
	
	/**
	 * 获取添加页面
	 * @return
	 */
	public String getAddPageUrl() {
		return this.getTargetTypeName()  + "/" + this.getTargetTypeName() + "Add";
	}
	
	/**
	 * 显示添加页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String showAddPage(HttpServletRequest request){
		this.beforeShowAdd(request);
		return getAddPageUrl();
	}
	
	/**
	 * 显示添加页预处理
	 * @param request
	 */
	protected void beforeShowAdd(HttpServletRequest request) {
		
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	public String getEditPageUrl() {
		return this.getTargetTypeName() + "/" + this.getTargetTypeName() + "Edit";
	}
	
	/**
	 * 显示编辑页
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String showEditPage(HttpServletRequest request, Long id){
		T entity = this.getTargetService().selectByPrimaryKey(id);
		request.setAttribute("id", id);
		request.setAttribute("entity", entity);
		this.beforeShowUpdate(request, entity);
		return getEditPageUrl();
	}
	
	/**
	 * 显示编辑页预处理
	 * @param request
	 * @param entity
	 */
	public void beforeShowUpdate(HttpServletRequest request, T entity) {
		
	}
	
	/**
	 * 获取详情页
	 * @return
	 */
	public String getDetailPageUrl() {
		return this.getTargetTypeName() + "/" + this.getTargetTypeName() + "Detail";
	}
	
	/**
	 * 显示详情页
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/detailPage")
	public String showDetailPage(HttpServletRequest request, Long id){
		T entity = this.getTargetService().selectByPrimaryKey(id);
		request.setAttribute("id", id);
		request.setAttribute("entity", entity);
		this.beforeShowDetail(request, entity);
		return getEditPageUrl();
	}
	
	/**
	 * 显示详情预处理
	 * @param request
	 * @param id
	 */
	protected void beforeShowDetail(HttpServletRequest request, T entity) {
		
	}
	
	/**
	 * 列表页面
	 * @return
	 */
	public String getListPageUrl() {
		return this.getTargetTypeName() + "/" + this.getTargetTypeName();
	}
	
	/**
	 * 显示列表页
	 * @param request
	 * @return
	 */
	@RequestMapping("/showList")
	public String showList(HttpServletRequest request){
		this.beforeShowList(request);
		return getListPageUrl();
	}
	
	/**
	 * 显示列表预处理
	 * @param request
	 */
	protected void beforeShowList(HttpServletRequest request) {
		
	}
	
	/**
	 * 查询列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object list(HttpServletRequest request) {
		PageQuery pageQuery = new PageQuery();
		//pageNo
		String pageNo = request.getParameter("pageNo");
		if(!StringUtils.isEmpty(pageNo)) {
			pageQuery.setPageNo(Integer.parseInt(pageNo));
		}
		//pageSize
		String pageSize = request.getParameter("pageSize");
		if(!StringUtils.isEmpty(pageSize)) {
			pageQuery.setPageSize(Integer.parseInt(pageSize));
		}
		//orderBy
		String orderBy = request.getParameter("orderBy");
		if(!StringUtils.isEmpty(orderBy)) {
			pageQuery.setOrderBy(orderBy);
		}
		//conditions
		String conditions = request.getParameter("conditions");
		if(!StringUtils.isEmpty(conditions)) {
			List<PageCondition> conditionList = JSONObject.parseArray(conditions, PageCondition.class);
			pageQuery.setConditions(conditionList);
		}
		//properties
		String properties = request.getParameter("properties");
		if(!StringUtils.isEmpty(properties)) {
			List<String> propertieList = JSONObject.parseArray(properties, String.class);
			String[] propertieArray = new String[propertieList.size()];
			propertieList.toArray(propertieArray);
			pageQuery.setProperties(propertieArray);
		}
		//获取列表前处理
		this.beforeList(request, pageQuery);
		PageResult<T> pageResult = this.getTargetService().select(pageQuery);
		return this.afterList(request, pageResult);
	}
	
	/**
	 * 获取列表前处理
	 * @param request
	 * @param pageQuery
	 * @return
	 */
	public Return<String> beforeList(HttpServletRequest request, PageQuery pageQuery){
		return new Return<>(true, "请求成功");
	}
	
	/**
	 * 获取列表后处理(数据转换)
	 * @param page
	 * @return
	 */
	protected Object afterList(HttpServletRequest request, PageResult<T> page) {
		return page;
	}
	
	/**
	 * 添加实体
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object save(HttpServletRequest request, T entity){
		//添加前处理
		Return<String> ret = this.beforeSave(request, entity);
		if(!ret.isSuccess()) {
			return this.renderFail(ret.getMsg());
		}
		//执行添加
		this.getTargetService().insertSelective(entity);
		//添加后处理
		this.afterSave(request, entity);
		return this.renderSuccess(entity, "添加成功!");
	}
	
	/**
	 * 添加前处理
	 * @param request
	 * @param entity
	 * @return
	 */
	protected Return<String> beforeSave(HttpServletRequest request, T entity){
		return new Return<>(true, "请求成功");
	}
	
	/**
	 * 添加后处理
	 * @param request
	 * @param entity
	 */
	protected void afterSave(HttpServletRequest request, T entity) {
		
	}
	
	/**
	 * 更新数据
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object update(HttpServletRequest request, T entity) {
		//更新前处理
		Return<String> ret = this.beforeUpdate(request, entity);
		if(!ret.isSuccess()) {
			return this.renderFail(ret.getMsg());
		}
		//执行更新
		if(this.getTargetService().updateSelective(entity) > 0) {
			this.afterUpdate(request, entity);
			return this.renderSuccess(entity, "保存成功!");
		}else {
			return this.renderFail("保存失败!");
		}
	}
	
	/**
	 * 更新前处理
	 * @param entity
	 * @return
	 */
	protected Return<String> beforeUpdate(HttpServletRequest request, T entity){
		return new Return<>(true, "请求成功");
	}
	
	/**
	 * 更新后处理
	 * @param request
	 * @param entity
	 */
	protected void afterUpdate(HttpServletRequest request, T entity) {
		
	}
	
	/**
	 * 删除数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request) {
		String ids = request.getParameter("id");
		if(StringUtils.isEmpty(ids)) {
			ids = request.getParameter("ids");
		}
		if(StringUtils.isEmpty(ids)) {
			return this.renderFail("请选择需要删除的记录!");
		}
		List<Long> idArray = JSONObject.parseArray(ids, Long.class);
		if(idArray == null || idArray.size() == 0) {
			return this.renderFail("请选择需要删除的记录!");
		}
		//删除前验证
		for(Long id : idArray) {
			//删除前处理
			Return<String> ret = this.beforeDelete(id);
			if(!ret.isSuccess()) {
				return this.renderFail(ret.getMsg());
			}
		}
		int count = 0;
		for(Long id : idArray) {
			if(this.getTargetService().deleteByPrimaryKey(id) > 0) {
				//删除后处理
				this.afterDelete(id);
				count ++;
			}
		}
		if(count > 0) {
			return this.renderSuccess(count, "删除成功!");
		}else {
			return this.renderFail("删除失败!");
		}
	}
	
	/**
	 * 删除前处理
	 * @param id
	 * @return
	 */
	protected Return<String> beforeDelete(Long id) {
		return new Return<>(true, "");
	}
	
	/**
	 * 删除后处理
	 * @param id
	 */
	protected void afterDelete(Long id) {
		
	}
	
	/**
	 * 详情
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public Object detail(HttpServletRequest request, Long id) {
		Return<String> ret = this.beforeDetail(request, id);
		if(!ret.isSuccess()) {
			return this.renderFail(ret.getMsg());
		}
		//执行查询
		T entity = this.getTargetService().selectByPrimaryKey(id);
		if(entity == null) {
			return this.renderFail("查询数据失败!");
		}else {
			Object result = this.afterDetail(entity);
			return this.renderSuccess(result, "查询成功!");
		}
	}
	
	/**
	 * 详情预处理
	 * @param request
	 * @param id
	 * @return
	 */
	protected Return<String> beforeDetail(HttpServletRequest request, Long id) {
		return new Return<>(true, "");
	}
	
	/**
	 * 详情数据转换
	 * @param entity
	 * @return
	 */
	protected Object afterDetail(T entity) {
		return entity;
	}
	/**
	 * 获取默认排序
	 * @return
	 */
	public String getDefaultOrderBy() {
		return "id desc";
	}

	/**
	 * 获取当前登录用户对象
	 *
	 * @return
	 */
	public User getCurrentUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		User currentUser = userService.selectByPrimaryKey(user.getId());
		return currentUser;
	}
}
