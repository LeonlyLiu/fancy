package jp.ac.kagawa_u.infoexpr.Device.InputDevice.Sensor;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

public class ColorSensor extends EV3ColorSensor implements Sensor
{

    private SensorMode lightMode = this.getMode(1);
    private SensorMode colorMode = this.getMode(2);
    private final static int HSV_SIZE = 3;

    float thresholdWhite = 0.9F;
    float thresholdBlack = 0.1F;
    float thresholdMono = 0.3F;

    /*---------------------------------------------------------
    ColorSensorのコンストラクタ
    @param Port
    SensorPort
     ---------------------------------------------------------*/

    public ColorSensor(Port port)
    {
        super(port);
    }

    /*---------------------------------------------------------
    光量(反射光)の値を取得(光量モードを使用)
    @return float 反射光の値が格納されたfloat型
    範囲は、0.0から(最大値は不明)
    ---------------------------------------------------------*/

    public float getLight()
    {
        float[] result = new float[lightMode.sampleSize()];
        lightMode.fetchSample(result, 0);
        return result[0];
    }

    /*---------------------------------------------------------
    センサの計測値をデータ列として取得し、配列として返却(色彩モードを使用)
    @return float[] RGB値が格納されたfloat型の配列
    範囲は、0.0から256.0未満まで
    ---------------------------------------------------------*/

    public float[] getValue()
    {
        float[] result = new float[colorMode.sampleSize()];
        colorMode.fetchSample(result, 0);
        return result;
    }

    /*---------------------------------------------------------
    RGBの各成分を取得
    @return float RGBが格納されたfloat型配列
    ---------------------------------------------------------*/

    public float[] getRGB()
    {
        return this.getValue();
    }

    // 赤成分
    public float getRed()
    {
        return this.getValue()[0];
    }

    // 緑成分
    public float getGreen()
    {
        return this.getValue()[1];
    }

    // 青成分
    public float getBlue()
    {
        return this.getValue()[2];
    }

    /*---------------------------------------------------------
    RGB値からの輝度の計算
    RGB値は、3:6:1の比率
    ---------------------------------------------------------*/

    public float getBrightnessByRGB()
    {
        float[] rgb = new float[3];
        rgb = this.getRGB();
        return 0.299F * rgb[0] + 0.587F * rgb[1] + 0.114F * rgb[2];
    }

    /*---------------------------------------------------------
    HSLの3つの計算値を結果のデータ列hsl[]に保存

    HSLは双円錐モデルの色相(Hue)、彩度(Saturation)、輝度(Light)
    色相 hsl[0] : 色味を0～360度の範囲の角度で表す。0は赤、120は緑、240は青
    彩度 hsl[1] : 純色100から無彩色0までの値 (純白と純黒は0)
    輝度 hsl[2] : 光量としての明るさで、白100、灰と純色50、黒0
    RGBよりも外光や影の影響に強い
    ---------------------------------------------------------*/

    public float[] getHSL()
    {
        float[] rgb = new float[HSV_SIZE];
        rgb = this.getRGB();
        final float min = this.minRGB(rgb);
        final float max = this.maxRGB(rgb);
        final float range = max - min;
        final float sum = max + min;
        final float index = this.minIndexOfRGB(rgb);
        float[] hsl = { 0.0F, 0.0F, 0.0F };
        float hue = -1.0F;

        if (range == 0.0F)
            hsl[0] = -1.0F;
        else if (index == 0)
        {
            hue = 60 * (rgb[2] - rgb[1]) / range + 180;
        } else if (index == 1)
        {
            hue = 60 * (rgb[0] - rgb[2]) / range + 300;
        } else
        {
            hue = 60 * (rgb[1] - rgb[0]) / range + 60;
        }

        hsl[0] = hue;
        hsl[1] = range;
        hsl[2] = sum / 2;

        return hsl;
    }

    /*---------------------------------------------------------
    HSVの3つの計算値を結果のデータ列hsv[]に保存

    HSVは円柱モデルの色相(Hue)、彩度(Saturation)、明度(Value)
    色相 hsv[0] : 色味を0～360度の範囲の角度で表す。0は赤、120は緑、240は青
    彩度 hsv[1] : 純色100から無彩色0までの値
    明度 hsv[2] : 色彩としての明るさで、白と純色100、灰50、黒0
    HSLとは純色の明度が異なる。純黒は彩度0とする。
    ---------------------------------------------------------*/

    public float[] getHSV()
    {
        float[] rgb = new float[HSV_SIZE];
        rgb = this.getRGB();
        final float min = this.minRGB(rgb);
        final float max = this.maxRGB(rgb);
        final float range = max - min;
        final float index = this.minIndexOfRGB(rgb);
        float[] hsv = { 0.0F, 0.0F, 0.0F };
        float hue = -1.0F;

        if (range == 0.0F)
            hsv[0] = -1.0F;
        else if (index == 0)
        {
            hue = 60 * (rgb[2] - rgb[1]) / range + 180;
        } else if (index == 1)
        {
            hue = 60 * (rgb[0] - rgb[2]) / range + 300;
        } else
        {
            hue = 60 * (rgb[1] - rgb[0]) / range + 60;
        }

        hsv[0] = hue;
        if (max == 0.0F)
        {
            hsv[1] = 0.0F;
        } else
        {
            hsv[1] = range / max;
        }
        hsv[2] = max;

        return hsv;
    }

    // 色相の成分
    public float getHue()
    {
        return this.getHSL()[0];
    }

    // 輝度の成分(HSL)
    public float getBrightnessByHSL()
    {
        return this.getHSL()[2];
    }

    // 輝度の成分(HSV)
    public float getBrightnessByHSV()
    {
        return this.getHSV()[2];
    }

    /*---------------------------------------------------------
    色彩モデルの変換のための補助メソッド
    ---------------------------------------------------------*/

    // RGBの最小値の取得
    private float minRGB(float[] rgb)
    {
        float min = rgb[0];
        if (min > rgb[1])
            min = rgb[1];
        if (min > rgb[2])
            min = rgb[2];
        return min;
    }

    // RGBの最大値の取得
    private float maxRGB(float[] rgb)
    {
        float max = rgb[0];
        if (max < rgb[1])
            max = rgb[1];
        if (max < rgb[2])
            max = rgb[2];
        return max;
    }

    // RGB値の
    private int minIndexOfRGB(float[] rgb)
    {
        int index = 0;
        float min = rgb[0];
        if (min > rgb[1])
        {
            min = rgb[1];
            index = 1;
        }
        if (min > rgb[2])
        {
            index = 2;
        }
        return index;
    }

    /*---------------------------------------------------------
    HSLにおける有彩色と無彩色の閾値
    ---------------------------------------------------------*/

    public void setThresholdWhite(float threshold)
    {
        thresholdWhite = threshold;
    }

    public void setThresholdBlack(float threshold)
    {
        thresholdBlack = threshold;
    }

    public void setThresholdNoncolor(float threshold)
    {
        thresholdMono = threshold;
    }

    /*---------------------------------------------------------
    HSLにおける色彩コードの判別(9色)
    ---------------------------------------------------------*/

    public int getColorCode()
    {

        float[] hsl = new float[3];
        hsl = this.getHSL();
        float hue = hsl[0];

        int colorId = -1;
        // WHITE
        if (hsl[2] >= this.thresholdWhite)
            colorId = 7;
        // BLACK
        else if (hsl[2] <= thresholdBlack)
            colorId = 0;
        // GRAY
        else if (hsl[1] <= thresholdMono)
            colorId = 8;
        // RED
        else if (330 >= hue && hue < 30)
            colorId = 0;
        // YELLOW
        else if (30 <= hue && hue < 90)
            colorId = 1;
        // GREEN
        else if (90 <= hue && hue < 150)
            colorId = 2;
        // CYAN
        else if (150 <= hue && hue < 210)
            colorId = 3;
        // BLUE
        else if (210 <= hue && hue < 270)
            colorId = 4;
        // MAGANTA
        else if (270 <= hue && hue < 330)
            colorId = 5;

        return colorId;
    }

    /*---------------------------------------------------------
    HSLにおける色彩コードから色名の取得(9色)
    ---------------------------------------------------------*/

    public String getColorName()
    {
        final int colorCode = this.getColorCode();
        switch (colorCode)
        {

        case 0:
            return "RED";
        case 1:
            return "YELLOW";
        case 2:
            return "GREEN";
        case 3:
            return "CYAN";
        case 4:
            return "BLUE";
        case 5:
            return "MAGENTA";
        case 6:
            return "BLACK";
        case 7:
            return "GRAY";
        case 8:
            return "WHITE";
        }
        return "ERROR";
    }

    public boolean isColorful()
    {
        final int colorCode = this.getColorCode();
        if (colorCode >= 0 && colorCode <= 5)
        {
            return true;
        }
        return false;
    }

}
