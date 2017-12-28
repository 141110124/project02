function checkall(form)
{
   var cbxoption;   
   cbxoption  =  form.elements;   
   for(i=0;i<cbxoption.length;i++)   
   {  
	   if((cbxoption[i].tagName.toLowerCase() ==  "input")  && (cbxoption[i].type) &&  (cbxoption[i].type.toLowerCase() ==  "checkbox"))   
	   {   
			cbxoption[i].checked = true;   
	   } 
   }
       
}
function uncheckall(form)
{
   var cbxoption;   
   cbxoption  =  form.elements;   
   for(i=0;i<cbxoption.length;i++)   
   {   
	   if((cbxoption[i].tagName.toLowerCase() ==  "input")  && (cbxoption[i].type) &&  (cbxoption[i].type.toLowerCase() == "checkbox") )   
	   {   
			cbxoption[i].checked = false;   
	   } 
   }  
}
function deletegrade(form)
{
   var stuids;
   var cbxoption;
   var m = 0;   
   cbxoption  =  form.elements;   
   for(i=0;i<cbxoption.length;i++)   
   {   
	   if((cbxoption[i].tagName.toLowerCase() ==  "input")  && (cbxoption[i].type) &&  (cbxoption[i].type.toLowerCase()  == "checkbox")    )   
	   {	   	 
	   	 if (cbxoption[i].checked)
	   	 {
	   	 	m ++;
	   	 	if (m == 1)
	   	 		stuids = cbxoption[i].id;
	   	 	else
	   	 		stuids +=  "," + cbxoption[i].id;
		  }
	   } 
	   
	   form.studentids.value = stuids;
   }
    if (m == 0)
	{
		alert("你没有选中需要删除的学生成绩，请重选！");
		return;
	}
	else
	{
		if(!confirm("是否将选中的学生成绩删除?")){
	   	  	return; 
	   }
	}
	 
	location.href="deleteGrade.do?stuids="+stuids+"&rnd="+new Date().getTime();
}

function modifygrade(form)
{
   var cbxoption,studid,username;   
   cbxoption  =  form.elements; 
   var m = 0;  
   for(i=0;i<cbxoption.length;i++)   
   {   
	   if((cbxoption[i].tagName.toLowerCase() ==  "input")  && (cbxoption[i].type) &&  (cbxoption[i].type.toLowerCase()== "checkbox")    )   
	   {   
		   	if (cbxoption[i].checked)
		   	 {
				m++;
				if (m > 1)
				{
					alert("一次只能维护一个学生的成绩，请重选！");
					return;
				}  
				studid = cbxoption[i].id;
		  }
	   } 
   }
   
    if (m == 0)
	{
		alert("你没有选中需要维护成绩的学生，请重选！");
		return;
	} 
	location.href="addGrade.do?stuid="+studid+"&from="+form.from.value+"&rnd="+new Date().getTime();
}

function checkForm()
{
	if ((AtTrim(document.form["grade.numElectron"].value) != "") && !isValidNumber(document.form["grade.numElectron"].value)) {
		alert("学生的成绩应为数字，请重新输入!");
		document.form["grade.numElectron"].focus();
		return false;
	}
	if ((AtTrim(document.form["grade.numSoftware"].value) != "") && !isValidNumber(document.form["grade.numSoftware"].value)) {
		alert("学生的成绩应为数字，请重新输入!");
		document.form["grade.numSoftware"].focus();
		return false;
	}
		if ((AtTrim(document.form["grade.numSecurity"].value) != "") && !isValidNumber(document.form["grade.numSecurity"].value)) {
		alert("学生的成绩应为数字，请重新输入!");
		document.form["grade.numSecurity"].focus();
		return false;
	}
		if ((AtTrim(document.form["grade.numJava"].value) != "") && !isValidNumber(document.form["grade.numJava"].value)) {
		alert("学生的成绩应为数字，请重新输入!");
		document.form["grade.numJava"].focus();
		return false;
	}
		if ((AtTrim(document.form["grade.numDB"].value) != "") && !isValidNumber(document.form["grade.numDB"].value)) {
		alert("学生的成绩应为数字，请重新输入!");
		document.form["grade.numDB"].focus();
		return false;
	}
		if ((AtTrim(document.form["grade.numImage"].value) != "") && !isValidNumber(document.form["grade.numImage"].value)) {
		alert("学生的成绩应为数字，请重新输入!");
		document.form["grade.numImage"].focus();
		return false;
	}
		if ((AtTrim(document.form["grade.numDistributed"].value) != "") && !isValidNumber(document.form["grade.numDistributed"].value)) {
		alert("学生的成绩应为数字，请重新输入!");
		document.form["grade.numDistributed"].focus();
		return false;
	}
	if ((AtTrim(document.form["grade.numTest"].value) != "") && !isValidNumber(document.form["grade.numTest"].value)) {
		alert("学生的成绩应为数字，请重新输入!");
		document.form["grade.numTest"].focus();
		return false;
	}
	return true;
}

	function query(gradeForm) {
		
		//获取用户在页面指定输入框内输入的学生学号或姓名查询字符串
		var querystr = gradeForm.querystring.value;
				
		if (gradeForm.queryradio[0].checked) {
			gradeForm.queryoption.value = "0";
			if ((AtTrim(querystr) == "")) {
				alert("请输入需要查询成绩的学生学号或姓名，再进行查询!");
				gradeForm.querystring.focus();
				return;
			}
		} else if (gradeForm.queryradio[1].checked) {
			querystr = gradeForm.querystring1.value;
			gradeForm.queryoption.value = "1";
			if ((AtTrim(querystr) == "") || !isValidNumber(querystr)) {
				alert("需要查询成绩的学生分数为空或不是数字，请重新输入!");
				gradeForm.querystring1.value="";
				gradeForm.querystring1.focus();
				return;
			}
		} else if (gradeForm.queryradio[2].checked){
			querystr = gradeForm.querystring2.value;
			gradeForm.queryoption.value = "2";
			if ((AtTrim(querystr) == "") || !isValidNumber(querystr)) {
				alert("需要查询的名次为空或不是数字，请重新输入!");
				gradeForm.querystring2.value="";
				gradeForm.querystring2.focus();
				return;
			}
		}
//		新增代码------------------------------------------------------
		else if (gradeForm.queryradio[3].checked){
			querystr = gradeForm.querystring3.value;
//alert(" 用户输入的课程名为     =  "+querystr);
			gradeForm.queryoption.value = "3";
			if (AtTrim(querystr) == "") {
				alert("需要查询的学科名称为空，请重新输入!");
				gradeForm.querystring3.value="";
				gradeForm.querystring3.focus();
				return;
			}
		}
		
		//防止用户未输入查询条件就点击查询按钮
		if(querystr==""){
			alert("对不起，您还没有输入查询条件，请您在输入查询条件后再点击查询  ^~^! ");
			return false;
		}		
		//提交表单【 submit() 方法把表单数据提交到 Web 服务器】。
		gradeForm.submit();
}


	function selectoption(gradeForm, option) {

		if (option == 1)
			{
			gradeForm.queryradio[1].checked = true;
			gradeForm.queryoption="1";
			}
		else if (option == 2)
			{
			gradeForm.queryradio[2].checked = true;
			gradeForm.queryoption="2";
			}
/*新增代码    to  查询 学科平均分***************************************************************/
		else if (option == 3)
			{
			gradeForm.queryradio[3].checked = true;
			gradeForm.queryoption="3";
			}
/* end   *******************************************************************************/
		else
			{
			gradeForm.queryradio[0].checked = true;
			gradeForm.queryoption="0";
			}
}