package org.emoration.lifeedge.utils;

import org.emoration.lifeedge.controller.DTO.CourseDTO;
import org.emoration.lifeedge.pojo.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/11/29
 */

public class CourseParseUtil {

    // 函数：时间戳计算函数
    // 时间戳均以秒为最小单位
    private static final long classDurationTimestamp = 45 * 60;
    private static final long dayDurationTimestamp = 24 * 60 * 60;
    private static final long weekDurationTimestamp = dayDurationTimestamp * 7;
    private static final long[] classBeginAtOfDayTimestamp = {
            (8 * 60 + 20) * 60,
            (9 * 60 + 15) * 60,
            (10 * 60 + 20) * 60,
            (11 * 60 + 15) * 60,

            (14 * 60 + 00) * 60,
            (14 * 60 + 55) * 60,
            (15 * 60 + 50) * 60,
            (16 * 60 + 45) * 60,

            (19 * 60 + 00) * 60,
            (19 * 60 + 55) * 60,
            (20 * 60 + 50) * 60,

    };
    private static final long termBeginTimestamp = 1693152000;

    /**
     * 计算课程开始时间和结束时间
     *
     * @param weekNum            周数 1~22
     * @param classNum           课程节数 1~11
     * @param termBeginTimestamp 学期初的时间戳
     * @return int[2]={开始时间,结束时间}
     */
    private static long[] classTimestampCalculate(int weekNum, int weekDayNum, int classNum, long termBeginTimestamp) {
        return new long[]{
                termBeginTimestamp + (long) (weekNum - 1) * weekDurationTimestamp + (weekDayNum - 1) * dayDurationTimestamp + classBeginAtOfDayTimestamp[classNum - 1],
                termBeginTimestamp + (long) (weekNum - 1) * weekDurationTimestamp + (weekDayNum - 1) * dayDurationTimestamp + classBeginAtOfDayTimestamp[classNum - 1] + classDurationTimestamp
        };
    }

    private static final String[] splitString = {
            "1\n8:20-9:05\t",
            "2\n9:15-10:00\t",
            "3\n10:20-11:05\t",
            "4\n11:15-12:00\t",

            "5\n14:00-14:45\t",
            "6\n14:55-15:40\t",
            "7\n15:50-16:35\t",
            "8\n16:45-17:30\t",

            "9\n19:00-19:45\t",
            "10\n19:55-20:40\t",
            "11\n20:50-21:35\t",
    };

    private static final int[] colors = new int[]{
            0x00FF00, // "绿色",
            0x0000FF, // "蓝色",
            0xFFFF00, // "黄色",
            0x00FFFF, // "青色",
            0xFFA500, // "橙色",
            0xFFC0CB, // "粉色",
            0xA52A2A, // "棕色",
            0xFF1493, // "深粉色",
            0x00CED1, // "深蓝绿色",
            0xFFD700, // "金色",
            0x2E8B57, // "海洋绿",
            0xFF6347, // "火砖色",
            0x6A5ACD, // "蓝紫罗兰色",
            0x008B8B, // "暗青色",
            0xB22222, // "砖红",
            0x9932CC, // "深紫罗兰色",
            0x228B22, // "森林绿",
            0xFF4500, // "橙红色",
            0x4169E1, // "皇家蓝",
            0x8B4513, // "马鞍棕色",
            0x20B2AA, // "海洋绿",
            0x8A2BE2, // "蓝紫色",
            0xB8860B, // "暗金黄色"
    };


    public static List<Event> doParse(String fileContent) {

        try {
            // 读取字符串
//            String fileContent = readFileToString(
//                    "D:\\emoration\\dev\\src\\IDEA\\draft\\d1\\d1\\src\\main\\resources\\test_watch_course_list_.txt");
            // 选取有用的字符串，并判断是否合法
            String usefulString = getUsefulString(fileContent);
            // 分割为每行课程(周一~周天的第i节课的列表)，并判断是否合法
            List<String> courseLineList = splitString(usefulString);
            // 下面将每行课程分割为(周j的第i节课)(列表i*j)，并判断是否合法
            List<List<String>> courseList = splitCourseLine(courseLineList);
            // 声明一个7*11的数组用来存储课程
            String[][] courseArray = courseListToCourseArray(courseList);
            // 将课程列表转为事件列表
            List<Event> eventList = courseArray_to_eventList(courseArray, termBeginTimestamp);
            // 生成课程名->颜色的map
            Map<String, Integer> colorMap = new HashMap<>();
            for (Event value : eventList) {
                String name = value.getName();
                if (!colorMap.containsKey(name)) {
                    colorMap.put(name, colors[colorMap.size() % colors.length]);
                }
                value.setColor(colorMap.get(name));
            }
//            // 输出测试
//            for (Event event : eventList) {
//                System.out.println(event);
//            }
            return eventList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 读取文件内容到字符串
    private static String readFileToString(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    // 选取有用的字符串，并判断是否合法
    private static String getUsefulString(String fileContent) {
        int beginIndex = fileContent.indexOf(
                "节次\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六\t星期日") + "节次\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六\t星期日".length();
        int endIndex = fileContent.indexOf("备注");
        if (endIndex < 0) endIndex = fileContent.length();
        fileContent = fileContent.substring(beginIndex, endIndex);
        fileContent = fileContent.replace("上午\t", "");
        fileContent = fileContent.replace("下午\t", "");
        fileContent = fileContent.replace("晚上\t", "");
        return fileContent;
    }

    // 函数：分割为每行课程(周一~周天的第i节课的列表)，并判断是否合法
    private static List<String> splitString(String usefulString) {
        List<String> courseLineList = new ArrayList<>();
        for (int i = 0; i < splitString.length; i++) {
            // 获取所有的第一节课的信息(搜索第一个分割串到第二个分割串中间的内容)
            int beginIndex1 = usefulString.indexOf(splitString[i]) + splitString[i].length();
            if (beginIndex1 < 0) {
                System.out.println("error");
                break;
            }

            if (i == splitString.length - 1) {
                courseLineList.add(usefulString.substring(beginIndex1));
                break;
            }

            int endIndex1 = usefulString.indexOf(splitString[i + 1]);
            if (endIndex1 < 0) {
                System.out.println("error");
                break;
            }
            String courseLine = usefulString.substring(beginIndex1, endIndex1);
            courseLineList.add(courseLine);
        }
        // 分割为每行课程，分割完成
        return courseLineList;
    }

    // 函数：下面将每行课程分割为(周j的第i节课)(列表i*j)，并判断是否合法，返回一个List<List<String>>
    private static List<List<String>> splitCourseLine(List<String> courseLineList) {
        List<List<String>> courseList = new ArrayList<>();
        for (int i = 0; i < courseLineList.size(); i++) {
            courseList.add(new ArrayList<>());
            String[] courseLineArray = courseLineList.get(i).split("\\t");
            for (String courseLine : courseLineArray) {
                courseList.get(i).add(courseLine);
            }
        }
        // 填充
        while (courseList.size() < 11) {
            courseList.add(new ArrayList<>());
        }
        for (List<String> strings : courseList) {
            while (strings.size() < 8) {
                strings.add("");
            }
        }
        // 过多删除
        while (courseList.size() > 11) {
            courseList.remove(courseList.size() - 1);
        }
        for (List<String> strings : courseList) {
            while (strings.size() > 8) {
                strings.remove(strings.size() - 1);
            }
        }
        return courseList;
    }

    // 函数：将课程转置并输出到String[7][11]
    private static String[][] courseListToCourseArray(List<List<String>> courseList) {
        String[][] courseArray = new String[7][11];
        for (int i = 0; i < 11; i++) {
            for (int i1 = 1; i1 < 8; i1++) {
                // 记录到courseArray中
                courseArray[i1 - 1][i] = courseList.get(i).get(i1);
            }
        }
        return courseArray;
    }

    // 函数：将每节课时间的字符串多节课(有可能多节课同时上)
    private static String[][] courseString_to_courseStringList(String courseString) throws Exception {
        String[] split = courseString.split("\n");
        if (split.length % 5 != 0) {
            throw new Exception("课程行错误");
        }
        String[][] courseStringList = new String[split.length / 5][5];
        for (int i = 0; i < split.length; i++) {
            courseStringList[i / 5][i % 5] = split[i];
        }
        return courseStringList;
    }

    /**
     * 函数：将每节课(确定只有一节课)转为结构体，需要指定颜色
     *
     * @param courseString       一节课的课程string
     * @param weekDayNum         周数 1~22
     * @param classNum           课程节数 1~11
     * @param termBeginTimestamp 学期初的时间戳
     * @return 事件结构体列表
     * @throws Exception 解析错误报错
     */
    private static List<Event> courseToEvent(String[] courseString, long termBeginTimestamp, int weekDayNum, int classNum) throws Exception {
        if (courseString.length != 5) {
            throw new Exception("课程行错误");
        }
        String name = courseString[0];
        String place = courseString[2].substring(courseString[2].indexOf(" ") + 2, courseString[2].length() - 2);
        String evenOdd = courseString[2].indexOf(" ") != 0 ? courseString[2].substring(1, courseString[2].indexOf(" ") - 1) : "";
        String teacher = courseString[3];
        int weekBegin = Integer.parseInt(courseString[4].substring(0, courseString[4].indexOf("-"))); // FIXME: 有可能有0前缀
        int weekEnd = Integer.parseInt(courseString[4].substring(courseString[4].indexOf("-") + 1));

        List<Event> eventList = new ArrayList<>();
        for (int weekNum = weekBegin; weekNum <= weekEnd; weekNum++) {
            if (evenOdd.equals("单") && weekNum % 2 == 0) continue;
            if (evenOdd.equals("双") && weekNum % 2 == 1) continue;
            Event event = new Event();
            event.setName(name);
            event.setDescription("课程信息："
                    + "\n地点：" + place
                    + "\n单双周：" + (evenOdd.isEmpty() ? "单双" : evenOdd)
                    + "\n周数范围：" + weekBegin + "-" + weekEnd
                    + "\n老师：" + teacher
            );
            long[] courseTime = classTimestampCalculate(weekNum, weekDayNum, classNum, termBeginTimestamp);
            event.setBeginAt(courseTime[0]);
            event.setEndAt(courseTime[1]);

            eventList.add(event);
        }

        return eventList;
    }

    private static List<Event> courseArray_to_eventList(String[][] courseArray, long termBeginTimestamp) throws Exception {
        List<Event> eventList = new ArrayList<>();
        for (int i1 = 0; i1 < 7; i1++) {
            for (int i = 0; i < 11; i++) {
                if (courseArray[i1][i].isEmpty()) continue;
                String[][] courseStringList = courseString_to_courseStringList(courseArray[i1][i]);
                for (String[] courseString : courseStringList) {
                    List<Event> events = courseToEvent(courseString, termBeginTimestamp, i1 + 1, i + 1);
                    eventList.addAll(events);
                }
            }
        }
        return eventList;
    }

    public static List<Event> courseDTO_to_eventList(CourseDTO courseDTO) {
        List<Event> eventList = new ArrayList<>();
        for (int weekNum = courseDTO.getBeginAtWeek(); weekNum <= courseDTO.getEndAtWeek(); weekNum++) {
            if (courseDTO.getOddEven() == 2 && weekNum % 2 == 0) continue;
            if (courseDTO.getOddEven() == 1 && weekNum % 2 == 1) continue;

            Event event = new Event();
            event.setName(courseDTO.getName());
            event.setDescription("课程信息"
                    + "地点：" + courseDTO.getPlace()
                    + "\n单双周：" + (courseDTO.getOddEven() == 3 ? "单双" : courseDTO.getOddEven() == 2 ? "双" : "单")
                    + "\n周数范围：" + courseDTO.getBeginAtWeek() + "-" + courseDTO.getEndAtWeek()
                    + "\n老师：" + courseDTO.getTeacher()
                    + "\n备注：" + courseDTO.getDescription()
            );
            long[] courseTime = classTimestampCalculate(weekNum, courseDTO.getWeekDayNum(), courseDTO.getClassNum(), termBeginTimestamp);
            event.setColor(courseDTO.getColor());
            event.setBeginAt(courseTime[0]);
            event.setEndAt(courseTime[1]);
            eventList.add(event);
        }
        return eventList;
    }
}
/* 课程内容样例
软件工程实践
[教学大纲|授课计划]
[双] [旗山数计3-302]
程永利
08-14

数据库应用实践
[教学大纲|授课计划]
[单] [旗山数计3-304]
刘漳辉
09-15
 */