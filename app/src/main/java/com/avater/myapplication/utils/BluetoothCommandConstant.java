package com.avater.myapplication.utils;

import java.util.UUID;

/**
 * 作者：hsh
 * 日期：2017/3/20
 * 说明：蓝牙命令常量(蓝牙协议里的所有常量)和蓝牙操作的各种UUID
 */
public interface BluetoothCommandConstant {

    /*-----------------------------------------------蓝牙服务特征的UUID---------------------------------------------*/
    UUID UUID_CONFIG_DESCRIPTOR = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    UUID UUID_CHARACTERISTIC_8001 = UUID.fromString("00008001-0000-1000-8000-00805f9b34fb");
    UUID UUID_CHARACTERISTIC_8002 = UUID.fromString("00008002-0000-1000-8000-00805f9b34fb");
    UUID UUID_SERVICE_BASE = UUID.fromString("00006006-0000-1000-8000-00805f9b34fb");
    UUID UUID_CHARACTERISTIC_8003 = UUID.fromString("00008003-0000-1000-8000-00805f9b34fb");
    UUID UUID_CHARACTERISTIC_8004 = UUID.fromString("00008004-0000-1000-8000-00805f9b34fb");
    UUID UUID_SERVICE_EXTEND = UUID.fromString("00007006-0000-1000-8000-00805f9b34fb");
    UUID UUID_CHARACTERISTIC_HEART_RATE = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb");
    UUID UUID_SERVICE_HEART_RATE = UUID.fromString("0000180d-0000-1000-8000-00805f9b34fb");
    /*-----------------------------------------------------------------------------------------------------------*/

    /*-----------------------------------------------开始结束标志-------------------------------------------------*/
    byte FLAG_START = (byte) 0x6F;                                                                  // 开始标志
    byte FLAG_START_L28T = (byte) 0x6E;                                                                  // 开始标志
    byte FLAG_END = (byte) 0x8F;                                                                    // 结束标志
    /*-----------------------------------------------------------------------------------------------------------*/


    /*---------------------------------------------------动作-----------------------------------------------------*/
    byte ACTION_CHECK = (byte) 0x70;                                                                // 查询
    byte ACTION_SET = (byte) 0x71;                                                                  // 设置
    byte ACTION_CHECK_RESPONSE = (byte) 0x80;                                                       // 查询响应
    byte ACTION_SET_RESPONSE = (byte) 0x81;                                                         // 设置响应
    byte ACTION_CODE_L28T=(byte)0x01;                                                               //L28T查询
    byte ACTION_SET_CODE_L28T=(byte)0x02;                                                               //L28T设置
    /*-----------------------------------------------------------------------------------------------------------*/

    /*---------------------------------------------------L28T的命令码---------------------------------------------------*/
    //要注意的是这里是设备返回的命令码，不是我们发送的 比如设备版本发送的命令码是0x03,设备返回的命令码确实0x09,所以这里标志位0x09来使用
    //命令码同样不可怕，只要动作不一样就行了。就算都一样也不怕，设计如此。。。。。。
    byte COMMAND_CODE_L28T_SOFTWARE_VERSION = (byte) 0x09;                                          // 设备版本
    byte COMMAND_CODE_L28T_WATCH_ID = (byte) 0x04;                                                  // watchID L28T
    byte COMMAND_CODE_L28T_SET_UID = (byte) 0x4A;                                                   // BIND UID
    byte COMMAND_CODE_L28T_BIND_INFO = (byte) 0x12;                                                 // BIND 个人信息
    byte COMMAND_CODE_L28T_BASE_TIME = (byte) 0x15;                                                 // BIND 时间
    byte COMMAND_CODE_L28T_POWER = (byte) 0x00;                                                     //  电量
    byte COMMAND_CODE_L28T_SET_DLE = (byte) 0x32;                                                   // 设置删除
    byte COMMAND_CODE_L28T_ALL_DATA_COUNT = (byte) 0x12;                                            // 运动条数
    byte COMMAND_CODE_L28T_SLEEP_MODEL = (byte) 0x1E;                                               // 睡眠/运动状态
    byte COMMAND_CODE_L28T_STEP_DATE = (byte) 0x05;                                                 // 运动详细数据
    byte COMMAND_CODE_L28T_DELETE_STEP_DATE = (byte) 0x32;                                          // 运动删除
    byte COMMAND_CODE_L28T_SLEEP_COUNT = (byte) 0x15;                                               // 睡眠数据的条数
    byte COMMAND_CODE_L28T_VIBRATION = (byte) 0x4f;                                                 // 震动强度
    byte COMMAND_CODE_L28T_TIME_FORMAT = (byte) 0x34;                                               // 时间格式
    byte COMMAND_CODE_L28T_SET_NAME = (byte) 0x4D;                                                  // 设置名字
    byte COMMAND_CODE_L28T_PRESET_SLEEP = (byte) 0x36;                                              // 设置预设睡眠
    byte COMMAND_CODE_L28T_SLEEP_DATA = (byte) 0x13;                                                // 睡眠数据
    byte COMMAND_CODE_L28T_DLE_SLEEP_DATA = (byte) 0x32;                                                // 睡眠数据
    byte COMMAND_CODE_L28T_SET_RESET = (byte) 0x11;                                                // 睡眠数据
    byte COMMAND_CODE_L28T_ALERT = (byte) 0x43;                                                     // 久坐提醒
    byte COMMAND_CODE_L28T_GOLD = (byte) 0x4C;                                                     // 金币
    byte COMMAND_CODE_L28T_FIND_DEVICE = (byte) 0x4B;                                                     // 查找手环


    /*---------------------------------------------------命令码---------------------------------------------------*/
    byte COMMAND_CODE_RESPONSE = (byte) 0x01;                                                       // 响应
    byte COMMAND_CODE_WATCH_ID = (byte) 0x02;                                                       // watchID
    byte COMMAND_CODE_DEVICE_VERSION = (byte) 0x03;                                                 // 设备版本
    byte COMMAND_CODE_DATETIME = (byte) 0x04;                                                       // 日期时间
    byte COMMAND_CODE_TIME_SURFACE_SETTING = (byte) 0x05;                                           // 时间界面设置
    byte COMMAND_CODE_PRIMARY_SURFACE_DISPLAY_SETTING = (byte) 0x06;                                // 一级界面显示设置
    byte COMMAND_CODE_SCREEN_BRIGHTNESS_SETTING = (byte) 0x07;                                      // 屏幕亮度设置
    byte COMMAND_CODE_BATTERY_POWER = (byte) 0x08;                                                  // 电池电量
    byte COMMAND_CODE_VOLUME = (byte) 0x09;                                                         // 音量
    byte COMMAND_CODE_SHOCK_MODE = (byte) 0x0A;                                                     // 震动模式
    byte COMMAND_CODE_LANGUAGE = (byte) 0x0B;                                                       // 语言
    byte COMMAND_CODE_UNIT = (byte) 0x0C;                                                           // 单位
    byte COMMAND_CODE_RESTORE_FACTORY = (byte) 0x0D;                                                // 恢复出厂
    byte COMMAND_CODE_UPGRADE_MODE = (byte) 0x0E;                                                   // 升级模式
    byte COMMAND_CODE_SHOCK_STRENGTH = (byte) 0x10;                                                 // 震动强度
    byte COMMAND_CODE_MAIN_ALARM_BACKGROUND_COLOR = (byte) 0x11;                                    // 主界面和报警界面背景颜色
    byte COMMAND_CODE_WORK_MODE = (byte) 0x12;                                                      // 工作模式
    byte COMMAND_CODE_BRIGHT_SCREEN_TIME = (byte) 0x13;                                             // 亮屏时间

    byte COMMAND_CODE_USER_INFO = (byte) 0x30;                                                      // 个人信息
    byte COMMAND_CODE_USAGE_HABITS = (byte) 0x31;                                                   // 使用习惯
    byte COMMAND_CODE_USER_NAME = (byte) 0x32;                                                      // 用户名称

    byte COMMAND_CODE_GOAL = (byte) 0x50;                                                           // 目标设置
    byte COMMAND_CODE_SPORT_SLEEP_MODE = (byte) 0x51;                                               // 运动/睡眠模式
    byte COMMAND_CODE_TOTAL_SPORT_SLEEP_COUNT = (byte) 0x52;                                        // 运动/睡眠数量总数
    byte COMMAND_CODE_DELETE_SPORT_DATA = (byte) 0x53;                                              // 删除运动数据
    byte COMMAND_CODE_GET_SPORT_DATA = (byte) 0x54;                                                 // 获取运动数据
    byte COMMAND_CODE_DELETE_SLEEP_DATA = (byte) 0x55;                                              // 删除睡眠数据
    byte COMMAND_CODE_GET_SLEEP_DATA = (byte) 0x56;                                                 // 获取睡眠数据
    byte COMMAND_CODE_DEVICE_DISPLAY_DATA = (byte) 0x57;                                            // 设备端显示各种类型的值
    byte COMMAND_CODE_AUTO_SLEEP = (byte) 0x58;                                                     // 设置睡眠状态(自动睡眠、马上睡眠)
    byte COMMAND_CODE_TOTAL_HEART_RATE_COUNT = (byte) 0x59;                                         // 心率数量总数
    byte COMMAND_CODE_DELETE_HEART_RATE_DATA = (byte) 0x5A;                                         // 删除心率数据
    byte COMMAND_CODE_GET_HEART_RATE_DATA = (byte) 0x5B;                                            // 获取心率数据(一条命令获取一条数据)
    byte COMMAND_CODE_AUTO_HEART_RATE = (byte) 0x5C;                                                // 设置自动心率
    byte COMMAND_CODE_HEART_RATE_ALARM_THRESHOLD = (byte) 0x5D;                                     // 心率报警门限
    byte COMMAND_CODE_INACTIVITY_ALERT = (byte) 0x5E;                                               // 久坐提醒
    byte COMMAND_CODE_GET_MOOD_DATA = (byte) 0x5F;                                                  // 获取情绪疲劳度数据
    byte COMMAND_CODE_CALORIES_TYPE = (byte) 0x60;                                                  // 卡路里类型
    byte COMMAND_CODE_GET_HEART_RATE_DATA_EX = (byte) 0x61;                                         // 获取心率数据(一条命令获取两条数据)
    byte COMMAND_CODE_TOTAL_BLOOD_PRESSURE_COUNT = (byte) 0x62;                                     // 血压数量总数
    byte COMMAND_CODE_DELETE_BLOOD_PRESSURE_DATA = (byte) 0x63;                                     // 删除血压数据
    byte COMMAND_CODE_GET_BLOOD_PRESSURE_DATA = (byte) 0x64;                                        // 获取血压数据
    byte COMMAND_CODE_BLOOD_PRESSURE_CHIP_LEARN = (byte) 0x65;                                      // 血压芯片学习
    byte COMMAND_CODE_TOTAL_REAL_TIME_SPORT_DATA_COUNT = (byte) 0x66;                               // 计时运动数量总数
    byte COMMAND_CODE_GET_REAL_TIME_SPORT_DATA = (byte) 0x67;                                       // 获取计时运动数据
    byte COMMAND_CODE_DELETE_REAL_TIME_SPORT_DATA = (byte) 0x68;                                    // 删除计时运动数据

    byte COMMAND_CODE_PHONE_NAME_PUSH = (byte) 0x70;                                                // 电话姓名推送
    byte COMMAND_CODE_SMS_PUSH = (byte) 0x71;                                                       // 短信推送
    byte COMMAND_CODE_MSG_COUNT_PUSH = (byte) 0x72;                                                 // 消息条数推送
    byte COMMAND_CODE_SOCIAL_COUNT_PUSH = (byte) 0x73;                                              // 社交推送
    byte COMMAND_CODE_EMAIL_PUSH = (byte) 0x74;                                                     // 邮件推送
    byte COMMAND_CODE_SCHEDULE_PUSH = (byte) 0x75;                                                  // 日程推送

    byte COMMAND_CODE_SWITCH_SETTING = (byte) 0x90;                                                 // 开关设置
    byte COMMAND_CODE_REMIND_COUNT = (byte) 0x91;                                                   // 提醒条数
    byte COMMAND_CODE_REMIND_SETTING = (byte) 0x92;                                                 // 提醒设置
    byte COMMAND_CODE_BIND_START = (byte) 0x93;                                                     // 绑定开始
    byte COMMAND_CODE_BIND_END = (byte) 0x94;                                                       // 绑定结束

    byte COMMAND_CODE_PAY_CARDNUMBER = (byte) 0xB0;                                                 // 支付:当前使用的芯片应用卡号
    byte COMMAND_CODE_PAY_MONEY = (byte) 0xB1;                                                      // 支付:余额
    byte COMMAND_CODE_PAY_RECORD = (byte) 0xB2;                                                     // 支付:交易记录
    byte COMMAND_CODE_PAY_PASSTHROUGH = (byte) 0xB3;                                                // 支付:透传

    byte COMMAND_CODE_MUSIC = (byte) 0xD0;                                                          // 音乐
    byte COMMAND_CODE_TAKE_PHOTO = (byte) 0xD1;                                                     // 拍照
    byte COMMAND_CODE_FIND_PHONE = (byte) 0xD2;                                                     // 寻找手机
    byte COMMAND_CODE_PHONE_CONTACT = (byte) 0xD5;                                                  // 手机联系人

    byte COMMAND_CODE_SHOCK_TEST = (byte) 0xFA;                                                     // 震动测试
    byte COMMAND_CODE_WRITE_WATCHID = (byte) 0xFF;                                                  // 写WatchID

    byte SET_CHILD_GODL_SETTING = (byte) 0x24;                                                      //28K金币
    byte SET_CHILD_DEVICE_NAME = (byte) 0x23;

    /*-----------------------------------------------------------------------------------------------------------*/


    /*---------------------------------------------------返回值---------------------------------------------------*/
    int RESULT_CODE_SUCCESS = 0;                                                                    // 成功
    int RESULT_CODE_FAIL = 1;                                                                       // 失败
    int RESULT_CODE_PROTOCOL_ERROR = 2;                                                             // 协议解析错误
    int RESULT_CODE_CONTINUE_RECEIVE = 3;                                                           // 继续接收
    int RESULT_CODE_INDEXS_COMMAND = 4;                                                             // 索引号命令
    int RESULT_CODE_RE_SEND = 5;                                                                    // 重新发送命令
    int RESULT_CODE_ERROR = -1;                                                                     // 错误
    int RESULT_CODE_LARGE_BYTES_ERROR = -2;                                                         // 大字节接收错误
    int RESULT_CODE_EXCEPTION = -3;                                                                 // 异常
    int RESULT_CODE_BLUETOOTH_VAR_NULL = -4;                                                        // bluetoothVar为null
    /*-----------------------------------------------------------------------------------------------------------*/


    /*---------------------------------------------------提醒类型-------------------------------------------------*/
    byte REMIND_TYPE_EAT = (byte) 0x00;                                                             // 吃饭
    byte REMIND_TYPE_MEDICINE = (byte) 0x01;                                                        // 吃药
    byte REMIND_TYPE_DRINK = (byte) 0x02;                                                           // 喝水
    byte REMIND_TYPE_SLEEP = (byte) 0x03;                                                           // 睡觉
    byte REMIND_TYPE_AWAKE = (byte) 0x04;                                                           // 清醒
    byte REMIND_TYPE_SPORT = (byte) 0x05;                                                           // 运动
    byte REMIND_TYPE_MEETING = (byte) 0x06;                                                         // 会议
    byte REMIND_TYPE_CUSTOM = (byte) 0x07;                                                          // 自定义
    /*-----------------------------------------------------------------------------------------------------------*/


    /*---------------------------------------------------消息类型-------------------------------------------------*/
    byte MSG_PUSH_TYPE_MISS_CALL = (byte) 0x00;                                                     // 未接来电
    byte MSG_PUSH_TYPE_SMS = (byte) 0x01;                                                           // 短信
    byte MSG_PUSH_TYPE_SOCIAL = (byte) 0x02;                                                        // 社交
    byte MSG_PUSH_TYPE_EMAIL = (byte) 0x03;                                                         // 邮件
    byte MSG_PUSH_TYPE_CALENDAR = (byte) 0x04;                                                      // 农历
    byte MSG_PUSH_TYPE_INCOME_CALL = (byte) 0x05;                                                   // 来电
    byte MSG_PUSH_TYPE_OFF_CALL = (byte) 0x06;                                                      // 来电挂断

    byte MSG_PUSH_TYPE_WECHAT = (byte) 0x07;                                                        // 微信
    byte MSG_PUSH_TYPE_VIBER = (byte) 0x08;                                                         // Viber
    byte MSG_PUSH_TYPE_SNAPCHAT = (byte) 0x09;                                                      // Snapchat
    byte MSG_PUSH_TYPE_WHATSAPP = (byte) 0x0A;                                                      // WhatsApp
    byte MSG_PUSH_TYPE_QQ = (byte) 0x0B;                                                            // QQ
    byte MSG_PUSH_TYPE_FACEBOOK = (byte) 0x0C;                                                      // Facebook
    byte MSG_PUSH_TYPE_HANGOUTS = (byte) 0x0D;                                                      // Hangouts
    byte MSG_PUSH_TYPE_GMAIL = (byte) 0x0E;                                                         // Gmail
    byte MSG_PUSH_TYPE_MESSENGER = (byte) 0x0F;                                                     // Facebook Messenger
    byte MSG_PUSH_TYPE_INSTAGRAM = (byte) 0x10;                                                     // Instagram
    byte MSG_PUSH_TYPE_TWITTER = (byte) 0x11;                                                       // Twitter
    byte MSG_PUSH_TYPE_LINKEDIN = (byte) 0x12;                                                      // Linkedin
    byte MSG_PUSH_TYPE_UBER = (byte) 0x13;                                                          // Uber
    byte MSG_PUSH_TYPE_LINE = (byte) 0x14;                                                          // Line
    byte MSG_PUSH_TYPE_SKYPE = (byte) 0x15;                                                         // Skype
    /*-----------------------------------------------------------------------------------------------------------*/


    /*---------------------------------------------------开关类型-------------------------------------------------*/
    byte SWITCH_TYPE_ANTI = (byte) 0x00;                                                            // 防丢开关
    byte SWITCH_TYPE_AUTO_SYNC = (byte) 0x01;                                                       // 自动同步开关
    byte SWITCH_TYPE_SLEEP = (byte) 0x02;                                                           // 睡眠开关
    byte SWITCH_TYPE_AUTO_SLEEP = (byte) 0x03;                                                      // 自动睡眠开关
    byte SWITCH_TYPE_CALL = (byte) 0x04;                                                            // 来电开关
    byte SWITCH_TYPE_MISS_CALL = (byte) 0x05;                                                       // 未接来电开关
    byte SWITCH_TYPE_SMS = (byte) 0x06;                                                             // 短信开关
    byte SWITCH_TYPE_SOCIAL = (byte) 0x07;                                                          // 社交开关
    byte SWITCH_TYPE_EMAIL = (byte) 0x08;                                                           // 邮件开关
    byte SWITCH_TYPE_CALENDAR = (byte) 0x09;                                                        // 日程开关
    byte SWITCH_TYPE_SEDENTARY = (byte) 0x0A;                                                       // 久坐提醒开关
    byte SWITCH_TYPE_LOW_POWER = (byte) 0x0B;                                                       // 低电量提醒开关
    byte SWITCH_TYPE_SECOND_REMINDER = (byte) 0x0C;                                                 // 二次提醒开关
    byte SWITCH_TYPE_RING = (byte) 0x0D;                                                            // 铃声开关
    byte SWITCH_TYPE_RAISE_WAKE = (byte) 0x0E;                                                      // 抬手亮屏开关
    /*-----------------------------------------------------------------------------------------------------------*/


    /*---------------------------------------------------界面显示-------------------------------------------------*/
    int INTERFACE_DISPLAY_TIME = 1;                                                                 // 时间
    int INTERFACE_DISPLAY_STEP = 2;                                                                 // 步数
    int INTERFACE_DISPLAY_DISTANCE = 3;                                                             // 距离
    int INTERFACE_DISPLAY_CALORIE = 4;                                                              // 卡路里
    int INTERFACE_DISPLAY_SLEEP = 5;                                                                // 睡眠
    int INTERFACE_DISPLAY_CITYCARD = 6;                                                             // 城市一卡通
    int INTERFACE_DISPLAY_BANKCARD = 7;                                                             // 银行卡
    /*-----------------------------------------------------------------------------------------------------------*/


    /*---------------------------------------------------目标类型-------------------------------------------------*/
    byte GOAL_TYPE_STEP = (byte) 0x00;                                                              // 步数
    byte GOAL_TYPE_CALORIE = (byte) 0x01;                                                           // 卡路里
    byte GOAL_TYPE_DISTANCE = (byte) 0x02;                                                          // 距离
    byte GOAL_TYPE_SLEEP = (byte) 0x03;                                                             // 睡眠
    byte GOAL_TYPE_SPORT_TIME = (byte) 0x04;                                                        // 运动时长
    /*-----------------------------------------------------------------------------------------------------------*/


    /*---------------------------------------------------震动动作-------------------------------------------------*/
    int SHOCK_ACTION_ANTI = 0;                                                                      // 防丢失
    int SHOCK_ACTION_CLOCK = 1;                                                                     // 闹钟
    int SHOCK_ACTION_INCOME_CALL = 2;                                                               // 来电
    int SHOCK_ACTION_MISS_CALL = 3;                                                                 // 未接来电
    int SHOCK_ACTION_SMS = 4;                                                                       // 短信
    int SHOCK_ACTION_SOCIAL = 5;                                                                    // 社交
    int SHOCK_ACTION_EMAIL = 6;                                                                     // 邮件
    int SHOCK_ACTION_CALENDAR = 7;                                                                  // 日历
    int SHOCK_ACTION_SEDENTARY = 8;                                                                 // 久坐
    int SHOCK_ACTION_LOW_POWER = 9;                                                                 // 低电
    /*-----------------------------------------------------------------------------------------------------------*/


    /*---------------------------------------------------震动模式-------------------------------------------------*/
    int SHOCK_MODE_NO = 0;                                                                          // 不震动
    int SHOCK_MODE_SIGNAL_LONG = 1;                                                                 // 单次长震动
    int SHOCK_MODE_SIGNAL_SHORT = 2;                                                                // 单次短震动
    int SHOCK_MODE_INTERVAL_TWO_LONG = 3;                                                           // 间隔2次长震动
    int SHOCK_MODE_INTERVAL_TWO_SHORT = 4;                                                          // 间隔2次短震动
    int SHOCK_MODE_INTERVAL_LONG_SHORT = 5;                                                         // 长震动和短震动交替
    int SHOCK_MODE_ALWAY_LONG = 6;                                                                  // 一直长震动
    int SHOCK_MODE_ALWAY_SHORT = 7;                                                                 // 一直短震动
    int SHOCK_MODE_INTERVAL_FIVE_LONG = 8;                                                          // 间隔5次长震动
    /*-----------------------------------------------------------------------------------------------------------*/


    /*---------------------------------------------------工作模式-------------------------------------------------*/
    byte WORK_MODE_POWER_SAVE = 0;                                                                  // 省电模式
    byte SHOCK_MODE_FLIGHT = 1;                                                                     // 飞行模式
    byte SHOCK_MODE_DEFAULT = 2;                                                                    // 默认模式
    byte SHOCK_MODE_custom = 3;                                                                     // 自定义模式
    /*-----------------------------------------------------------------------------------------------------------*/


    /*-------------------------------------------------绑定开始模式-----------------------------------------------*/
    byte BIND_START_SET_UID = 0;                                                                    // 绑定开始(设置UID)
    byte BIND_START_NO_UID = 1;                                                                     // 绑定开始(不用设置UID)
    /*-----------------------------------------------------------------------------------------------------------*/


    /*-----------------------------------------------电话姓名推送类型----------------------------------------------*/
    byte PHONE_NAME_PUSH_TYPE_INCOME_CALL = (byte) 0x00;                                            // 来电
    byte PHONE_NAME_PUSH_TYPE_REDIAL_CALL = (byte) 0x01;                                            // 重拨
    byte PHONE_NAME_PUSH_TYPE_MISS_CALL = (byte) 0x02;                                              // 未接来电
    byte PHONE_NAME_PUSH_TYPE_MISS_CALL_DATETIME = (byte) 0x03;                                     // 未接来电日期时间
    /*-----------------------------------------------------------------------------------------------------------*/


    /*------------------------------------------------短信消息类型------------------------------------------------*/
    byte SMS_PUSH_TYPE_NAME_OR_NUMBER = (byte) 0x00;                                                // 姓名或姓名
    byte SMS_PUSH_TYPE_CONTENT = (byte) 0x01;                                                       // 内容
    byte SMS_PUSH_TYPE_DATETIME = (byte) 0x02;                                                      // 日期时间
    /*-----------------------------------------------------------------------------------------------------------*/


    byte SOCIAL_PUSH_TYPE_CONTENT = (byte) 0x01;                                                    // 内容
    /*------------------------------------------------社交消息类型------------------------------------------------*/
    byte SOCIAL_PUSH_TYPE_TITLE = (byte) 0x00;                                                      // 标题
    byte SOCIAL_PUSH_TYPE_DATETIME = (byte) 0x02;                                                   // 日期时间
    /*-----------------------------------------------------------------------------------------------------------*/


    /*------------------------------------------------邮箱消息类型------------------------------------------------*/
    byte EMAIL_PUSH_TYPE_ADDRESS = (byte) 0x00;                                                     // 标题
    byte EMAIL_PUSH_TYPE_CONTENT = (byte) 0x01;                                                     // 内容
    byte EMAIL_PUSH_TYPE_DATETIME = (byte) 0x02;                                                    // 日期时间
    /*-----------------------------------------------------------------------------------------------------------*/


    /*------------------------------------------------日程消息类型------------------------------------------------*/
    byte SCHEDULE_PUSH_TYPE_CONTENT = (byte) 0x01;                                                  // 内容
    byte SCHEDULE_PUSH_TYPE_DATETIME = (byte) 0x02;                                                 // 日期时间
    /*-----------------------------------------------------------------------------------------------------------*/


    /*---------------------------------------------------音乐状态-------------------------------------------------*/
    byte REMOTE_MUSIC_CHECK_MUSIC_STATE = (byte) 0x00;                                              // 查询音乐状态
    byte REMOTE_MUSIC_SET_PHONE_PLAY = (byte) 0x00;                                                 // 设置手机播放
    byte REMOTE_MUSIC_SET_PHONE_PAUSE = (byte) 0x01;                                                // 设置手机暂停
    byte REMOTE_MUSIC_SET_PHONE_PRE_SONG = (byte) 0x02;                                             // 设置手机上一曲
    byte REMOTE_MUSIC_SET_PHONE_NEXT_SONG = (byte) 0x03;                                            // 设置手机下一曲
    byte REMOTE_MUSIC_SET_PHONE_VOLUME = (byte) 0x04;                                               // 设置手机音量
    byte REMOTE_MUSIC_SEND_DEVICE_PLAY = (byte) 0x00;                                               // 发送设备播放
    byte REMOTE_MUSIC_SEND_DEVICE_PAUSE = (byte) 0x01;                                              // 发送设备暂停
    byte REMOTE_MUSIC_SEND_DEVICE_VOLUME = (byte) 0x02;                                             // 发送设备音量
    byte REMOTE_MUSIC_SEND_DEVICE_STOP = (byte) 0xFF;                                               // 发送设备无音乐
    /*-----------------------------------------------------------------------------------------------------------*/


    /*-----------------------------------------------------拍照---------------------------------------------------*/
    byte REMOTE_TAKE_PHONE_START = (byte) 0x00;                                                     // 启动拍照
    byte REMOTE_TAKE_PHONE_END = (byte) 0x01;                                                       // 停止拍照
    byte REMOTE_TAKE_PHONE_CONNECT_APP = (byte) 0x02;                                               // 连接APP相机
    /*-----------------------------------------------------------------------------------------------------------*/


    /*---------------------------------------------------寻找手机-------------------------------------------------*/
    byte REMOTE_FIND_PHONE_START = (byte) 0x00;                                                     // 开始寻找手机
    byte REMOTE_FIND_PHONE_END = (byte) 0x01;                                                       // 结束寻找手机
    /*-----------------------------------------------------------------------------------------------------------*/


    /*-------------------------------------------------0x81响应结果-----------------------------------------------*/
    byte RESPONSE_SUCCESS = 0;                                                                      // 成功
    byte RESPONSE_FAIL = 1;                                                                         // 失败
    byte RESPONSE_ERROR = 2;                                                                        // 协议错误
    /*-----------------------------------------------------------------------------------------------------------*/


    /*-----------------------------------------------------长度---------------------------------------------------*/
    int ACTION_SET_RESPONSE_LEN = 8;                                                                // 设置响应长度 固定为8
    int MSG_PUSH_TYPE_MAX_NAME_LEN = 90;                                                            // 消息推送姓名的最大长度
    int MSG_PUSH_TYPE_MAX_CONTENT_LEN = 120;                                                        // 消息推送内容的最大长度
    /*-----------------------------------------------------------------------------------------------------------*/
}
