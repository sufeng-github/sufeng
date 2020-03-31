package paul.sydney.controller.others;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import paul.sydney.model.Goods;

@Controller 
@RequestMapping("/fileop.htm") 
public class EasyPOIController {
 
    @ResponseBody
   	@RequestMapping("/export")
    public void export(HttpServletResponse response) {
        Goods goods1 = new Goods(110, "苹果", 1, new Date(), 0, "1");
        Goods goods2 = new Goods(111, "格子衫", 2, new Date(), 0, "0");
        Goods goods3 = new Goods(112, "拉菲红酒", 3, new Date(), 1, "1");
        Goods goods4 = new Goods(113, "玫瑰", 4, new Date(), 1, "0");
 
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);
        goodsList.add(goods4);
 
        //可以抽取为日期工具类
        Date date1 = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = df.format(date1);
 
        for (int i = 0; i < goodsList.size(); ++i) {
            //添加序号列
            goodsList.get(i).setOrder(i + 1);
            //Date类型日期转换
            goodsList.get(i).setDateStr(df.format(goodsList.get(i).getShelfLife()));
            //type转换成显示文字
            if (goodsList.get(i).getType() == 1) {
                goodsList.get(i).setTypeName("食品");
            } else if (goodsList.get(i).getType() == 2) {
                goodsList.get(i).setTypeName("服装");
            } else if (goodsList.get(i).getType() == 3) {
                goodsList.get(i).setTypeName("酒水");
            } else if (goodsList.get(i).getType() == 4) {
                goodsList.get(i).setTypeName("花卉");
            }
        }
 
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
        // 获取导出excel指定模版，第二个参数true代表显示一个Excel中的所有 sheet
        TemplateExportParams params = new TemplateExportParams("source/商品详情表.xls", true);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("date", date);//导出一般都要日期
        data.put("one", goods1);//导出一个对象
        data.put("list", goodsList);//导出list集合
 
        try {
            // 简单模板导出方法
            Workbook book = ExcelExportUtil.exportExcel(params, data);
            //下载方法
            export(response, book, "商品信息");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * export导出请求头设置
     *
     * @param response
     * @param workbook
     * @param fileName
     * @throws Exception
     */
    private static void export(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {
        response.reset();
        response.setContentType("application/x-msdownload");
        fileName = fileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
        ServletOutputStream outStream = null;
        try {
            outStream = response.getOutputStream();
            workbook.write(outStream);
        } finally {
            outStream.close();
        }
    }
}