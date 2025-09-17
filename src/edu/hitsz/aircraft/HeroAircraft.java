package edu.hitsz.aircraft; //规定了一个存放的文件夹

import edu.hitsz.bullet.BaseBullet;//引入子弹抽象子类
import edu.hitsz.bullet.HeroBullet;//引入英雄子弹具体类

import java.util.LinkedList;//引入链表
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */

//声明了一个全新的、公开的、继承抽象飞机类的具体类
public class HeroAircraft extends AbstractAircraft {
    /**攻击方式:单发|30点伤害|子弹向上飞 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;//定义private,保证子类安全性

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：-1，向下发射：1)
     */
    private int direction = -1;

    /**解释变量:
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */

    //构造方法:制造一架飞机,提供初始参数
    public HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);//调用父类的构造方法
    }

    @Override//重写(覆盖)父类的forward方法
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
        // Q:如何实现?
        // A:这是一个空方法,它的存在只是为了满足“飞机都会移动”这个约定，但具体实现是**游戏主循环通过监听鼠标事件**。
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        // 1. 准备一个空的弹药箱（链表）
        List<BaseBullet> res = new LinkedList<>();
        // 2. 计算子弹出生的初始位置和速度
        int x = this.getLocationX(); // 子弹的初始X坐标 = 飞机的X坐标
        int y = this.getLocationY() + direction*2; // 子弹的初始Y坐标 = 飞机的Y坐标 + (向上偏移2像素)
        int speedX = 0; // 子弹在X方向没有速度，直上直下
        int speedY = this.getSpeedY() + direction*5; // 子弹速度 = 飞机基准速度 + 方向*(-5或+5)

        // 3. 根据发射数量，循环产生每一颗子弹
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散(散射的逻辑实现)
            bullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);//新建子弹对象
            // 4. 将子弹返回给调用者
            res.add(bullet);
        }

        return res;
    }

}
