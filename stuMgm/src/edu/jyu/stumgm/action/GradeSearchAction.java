package edu.jyu.stumgm.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import edu.jyu.stumgm.bo.GradeBO;
import edu.jyu.stumgm.entity.Grade;


public class GradeSearchAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Grade> gradelist;
	//对成绩表进行操作的业务类对象
	private GradeBO gradeBO;
	private String querystring, querystring1, querystring2;
	
	//querystring3  用于保存   按课程名搜索   的  学科的学生平均成绩  的  名称字符串
	private String querystring3;
	//tempStr 用于临时保存从页面获取到的  querystring3 的值
	private String tempStr;
	//指定学科 总成绩
	private int sum;
	//指定学科平均分
	private int avg;
	//
	private int size;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getQuerystring3() {
		return querystring3;
	}
	public void setQuerystring3(String querystring3) {
		this.querystring3 = querystring3;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getAvg() {
		return avg;
	}
	public void setAvg(int avg) {
		this.avg = avg;
	}
	
	//gradequery.jsp 页面表单隐藏域传递的值
	private String queryoption;
	
	public String execute()
	{
		//查询出学生成绩表中的所有成绩记录
		List<Grade> glist = gradeBO.getAllGrade();
		
		//取消初次进入  gradequery.jsp 页面显示所有学生成绩记录
		if(querystring==null&&querystring1==null&&querystring2==null&&querystring3==null) 
			{
				//取到存储在成绩表中现有的所有学生成绩记录
				gradelist=glist;
//System.err.println("成绩表中的现有总记录条数为 : "+glist.size()+"----------------------------------------------------" );
				glist.clear();
				return SUCCESS;
			}
		/** 查询学号或姓名 */
		if(queryoption.equals("0"))
		{
			//取出成绩表中现有的所有学生成绩记录  
			gradelist=glist;
			
//System.err.println("过滤掉学号或者姓名不符合查询条件的记录........................................................");
			for(int i=0; i<glist.size(); )
			{
				Grade g=glist.get(i);
				if(!g.getStudent().getStuNumber().contains(querystring)&&!g.getStudent().getUsername().contains(querystring))
				{
					glist.remove(g);
				}
				else i++;
			}
		}
		else if(queryoption.equals("1")) /** 查询总成绩小于输入值的记录 */
		{
			//取出成绩表中现有的所有学生成绩记录  
			gradelist=glist;

			for(int i=0; i<glist.size(); i++)
			{
				Grade g=glist.get(i);
//System.err.println("size = "+glist.size()+" 总成绩为 "+g.getTotal() +"   输入的成绩值为  "+querystring1);
				if(g.getTotal()>=Integer.parseInt(querystring1))
				{
					//过滤掉总成绩大于用户输入值的记录
					glist.remove(i);
					
                //补充如下代码修复原系统中的一个 bug
					if(i>0){
						i=i-1;
					}else if(i==0){
						i=-1;
					}
//System.err.println("删除掉不符合搜索条件的记录后集合的   size 值为 : "+glist.size());
				}
			}
			
		}
		//当你输入的值为前   X【  注：X 小于  1】名的学生成绩，默认查出总成绩排名第一的那条记录
		else if(queryoption.equals("2")) /** 查询总成绩前..名 */
		{
			//取出成绩表中现有的所有学生成绩记录  
			gradelist=glist;
			
			List<Grade> lg=glist;
			for(int i=0; i<lg.size(); i++)
			{
				int max = i;
				for(int j=i+1; j<lg.size(); j++)
				{
					if(lg.get(j).getTotal()>lg.get(max).getTotal())
					{
						max=j;
					}
				}
				if(max!=i)
				{
					Grade g=lg.get(i);
					lg.set(i, lg.get(max));
					lg.set(max, g);
				}
			}
			int k=0;
			try{
				k = Integer.parseInt(querystring2);
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
			int i=1;
			for(int j=1; i<lg.size(); i++)
			{
				if(i>=1&&lg.get(i).getTotal()!=lg.get(i-1).getTotal()) 
					{
						j++;
						if(j>k) break;
					}
			}
			glist=lg.subList(0, i);
		}
		/*查询课程名为   XX 的学科平均成绩【学科名不存在的在页面显示查询出来的平均分为 0 】*/
		else if(queryoption.equals("3")){
			
			//取出成绩表中现有的所有学生成绩记录  
			gradelist=glist;
			
			//暂存从页面获取的查询字符串
			tempStr = querystring3;
			
			//将页面输入的学科名字转换成与数据库表对应的字段名
			if(querystring3.equals("电子技术")){
				querystring3="electron";
			}else if(querystring3.equals("软件工程")){
				querystring3="software";
			}else if(querystring3.equals("计算机网络与信息安全")){
				querystring3="security";
			}else if(querystring3.equals("Java程序设计")){
				querystring3="java";
			}else if(querystring3.equals("高级数据库")){
				querystring3="db";
			}else if(querystring3.equals("图形图像处理技术")){
				querystring3="img";
			}else if(querystring3.equals("分布计算与互联网技术")){
				querystring3="distributed";
			}else if(querystring3.equals("软件测试与自演化技术")){
				querystring3="test";
			}
			
			//计算学科总分数值，求出学科分数平均值
			size = glist.size();
			
//System.out.println("页面输入的课程名字符串 querystring3 = "+querystring3+ "=======================================================");
			for(int i=0; i<glist.size(); )
			{
				Grade g=glist.get(i); 
//System.err.println(g.getNumImage()+"_________________________________________________________________________");
				sum = sum + g.getCertainSubject(querystring3);
//System.err.println(querystring3+"  =  "+g.getCertainSubject(querystring3)+"_________________________________________________________________________");

				i++;
			}
//System.err.println("学科名为 "+querystring3+"的学科总分数为---------------------------------- "+sum);
			//查询的学科存在
			if(size>0){
				avg = sum/size;
			}else{
				//不让页面输出成绩表的记录，仅输出学科平均分
				glist.clear();
			}
			//不让页面输出成绩表的记录，仅输出学科平均分
			glist.clear();
System.err.println("学科平均分为 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ "+avg);
		}
		
		//将从页面获取到的学科名称回显到页面
		querystring3 = tempStr;
		
		//返回到页面 已经过滤掉不符合条件的学生成绩记录集合
		gradelist=glist;
		
		return SUCCESS;
	}

	public List<Grade> getGradelist() {
		return gradelist;
	}

	public void setGradelist(List<Grade> gradelist) {
		this.gradelist = gradelist;
	}

	public GradeBO getGradeBO() {
		return gradeBO;
	}

	public void setGradeBO(GradeBO gradeBO) {
		this.gradeBO = gradeBO;
	}

	public String getQuerystring() {
		return querystring;
	}

	public void setQuerystring(String querystring) {
		this.querystring = querystring;
	}

	public String getQuerystring1() {
		return querystring1;
	}

	public void setQuerystring1(String querystring1) {
		this.querystring1 = querystring1;
	}

	public String getQuerystring2() {
		return querystring2;
	}

	public void setQuerystring2(String querystring2) {
		this.querystring2 = querystring2;
	}

	public String getQueryoption() {
		return queryoption;
	}

	public void setQueryoption(String queryoption) {
		this.queryoption = queryoption;
	}

}