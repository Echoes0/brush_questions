package com.ll.brush_questions.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *  生成Mapper、Service、Impl文件
 */
public class CreateMybatisFile {

    private static final String PackageCatalog = "ll";
    private static final String itemName="brush_questions";
    static String[] s={
            "ChoiceQuestion",
            "Paper"
    };

    public static void GenerateMapper() throws IOException {



        for (String value : s) {


            File f1 = new File("src/main/java/com/" + PackageCatalog+"/"+itemName + "/mapper/" + value + "Mapper.java");

            if (f1.exists()) {
                System.out.println("文件已经存在");
                continue;
            } else {
                try {
                    if (f1.createNewFile()) {
                        System.out.println("文件创建成功");
                    } else {
                        System.out.println("文件创建失败");
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            String s1 = "package com." + PackageCatalog+"."+itemName + ".mapper;\n" +
                    "\n" +
                    "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" +
                    "import com." + PackageCatalog+"."+itemName + ".entity." + value + ";\n" +
                    "import org.apache.ibatis.annotations.Mapper;\n" +
                    "\n" +
                    "@Mapper\n" +
                    "public interface " + value + "Mapper extends BaseMapper<" + value + "> {\n" +
                    "}\n";

            byte[] bytes;
            bytes = s1.getBytes();
            FileOutputStream fos = new FileOutputStream(f1);
            fos.write(bytes);
            fos.close();
        }


    }


    public static void GenerateService() throws IOException {

        for (String value : s) {


            File f1 = new File("src/main/java/com/" + PackageCatalog+"/"+itemName + "/service/" + value + "Service.java");

            if (f1.exists()) {
                System.out.println("文件已经存在");
                continue;
            } else {
                try {
                    if (f1.createNewFile()) {
                        System.out.println("文件创建成功");
                    } else {
                        System.out.println("文件创建失败");
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            String s1 = "package com." + PackageCatalog+"."+itemName + ".service;\n" +
                    "\n" +
                    "import com.baomidou.mybatisplus.extension.service.IService;\n" +
                    "import com." + PackageCatalog+"."+itemName + ".entity." + value + ";\n" +
                    "\n" +
                    "public interface " + value + "Service extends IService<" + value + "> {\n" +
                    "}\n";
            byte[] bytes;
            bytes = s1.getBytes();
            FileOutputStream fos = new FileOutputStream(f1);
            fos.write(bytes);
            fos.close();
        }


    }

    public static void GenerateImpl() throws IOException {

        for (String value : s) {


            File f1 = new File("src/main/java/com/" + PackageCatalog+"/"+itemName + "/service/impl/" + value + "ServiceImpl.java");

            if (f1.exists()) {
                System.out.println("文件已经存在");
                continue;
            } else {
                try {
                    if (f1.createNewFile()) {
                        System.out.println("文件创建成功");
                    } else {
                        System.out.println("文件创建失败");
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            String s1 = "package com." + PackageCatalog+"."+itemName + ".service.impl;\n" +
                    "\n" +
                    "import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;\n" +
                    "import com." + PackageCatalog+"."+itemName + ".entity." + value + ";\n" +
                    "import com." + PackageCatalog+"."+itemName + ".mapper." + value + "Mapper;\n" +
                    "import com." + PackageCatalog+"."+itemName + ".service." + value + "Service;\n" +
                    "import org.springframework.stereotype.Service;\n" +
                    "\n" +
                    "@Service\n" +
                    "public class " + value + "ServiceImpl extends ServiceImpl<" + value + "Mapper, " + value + "> implements " + value + "Service {\n" +
                    "}";

            byte[] bytes;
            bytes = s1.getBytes();
            FileOutputStream fos = new FileOutputStream(f1);
            fos.write(bytes);
            fos.close();
        }


    }
}
