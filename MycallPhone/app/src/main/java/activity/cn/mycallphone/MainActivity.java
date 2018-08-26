package activity.cn.mycallphone;/*表明这个类放置于activity\cn\mycallphone文件夹下，若这个类不在该文件夹下，那么运行就会报错*/
/*import的作用是声明要调用的类文件名*/
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
/*public修饰符具有最大的访问权限，可以访问任何一个在CLASSPATH下的类、接口、异常等。它往往用于对外的情况，也就是对象或类对外的一种接口的形式*/
public class MainActivity extends AppCompatActivity {
    /*private修饰符的访问权限仅限于类的内部，是一种封装的体现，他们不希望被其他任何外部的类访问*/
    private EditText etPhone;
    private Button btnPhone;
    /*@Override是伪代码,表示重写(当然不写也可以)，不过写上有如下好处:
        1、可以当注释用,方便阅读；
        2、编译器可以给你验证@Override下面的方法名是否是你父类中所有的，如果没有则报错。*/
    @Override
    /*protected类主要的作用就是用来保护子类的。它的含义在于子类可以用它修饰的成员（子类可以随意调用），其他的不可以（外部类无法调用），它相当于传递给子类的一种继承的东西*/
    protected void onCreate(Bundle savedInstanceState) {
        /*在实际应用中，当一个Activity结束前，如果需要保存状态，就在onsaveInstanceState中，将状态数据以key-value的形式放入到savedInstanceState中。
        这样，当一个Activity被创建时，就能从onCreate的参数saved Insance State（保存实例状态）中获得状态数据。
        注：1、Bundle，是Android开发中的一个类，用于Activity之间传输数据用
            2、OnCreate是Android中的一个特别的函数，用来“表示一个窗口正在生成”。其不产生窗口，只是在窗口显示前设置窗口的属性如风格、位置颜色等。
        */
        super.onCreate(savedInstanceState);
        /*super.onCreate(savedInstanceState)是调用父类Activity的onCreate()方法。上述代码中的protected void onCreate()方法其实是覆写了基类（Activity类）的onCreate方法，super.onCreate()是在调用基类中的onCreate方法。而在子类的onCreate方法中，不能直接调用onCreate()
        ,因为这样做会产生递归，为了解决这一问题，java用super关键字表示超类的意思，当前类就是从超类继承而来的*/
        setContentView(R.layout.activity_main);
        /*R.layout.main是个布局文件即控件都是如何摆放如何显示的，setContentView就是设置一个Activity的显示界面，
        这句话意思就是设置这句话所在的Activity采用R.layout下的activity_main布局文件进行布局*/
        etPhone = (EditText) findViewById(R.id.et_phone_num);
        /*findViewById（）寻找界面元素，一般格式为findViewById（R.id.xxx）*/
        btnPhone = (Button) findViewById(R.id.btn_call_phone);

        btnPhone.setOnClickListener(new View.OnClickListener() {
         /*创立监听器，监听器是一个存在于View类下的接口，一般以On*****Listener命名，实现该接口需要重复写相应的On***(View v)方法*/
            @Override
            public void onClick(View v) {
                if (etPhone.getText().toString().trim() == null || etPhone.getText().toString().trim().equals("")) {
                    /*editText.getText()==null   ----------   判断用户的输入是否为空
                     "".equals(editText.getText().toString().trim())  ----判断用户有没有输入，trim()--头尾空白被滤掉*/
                    Toast.makeText(MainActivity.this, "对不起，电话不能为空", Toast.LENGTH_SHORT).show();
                    /*Toast 是一个 View 视图，快速的为用户显示少量的信息。 Toast 在应用程序上浮动显示信息给用户，
                    它永远不会获得焦点，不影响用户的输入等操作，主要用于 一些帮助 / 提示。Toast 最常见的创建方式是使用静态方法 Toast.makeText
                    注：Toast三个参数的意义：
                        参数1：大概来说就是上下文对象，在哪个对象中显示
                        参数2：你要显示的内容，这里你可以写点击了某个Item
                        参数3：显示的时间长度，LENGTH_SHORT显示比LENGTH_LONG短
                    */
                    return;
                } else if (etPhone.getText().toString().trim() != null && !(etPhone.getText().toString().trim().equals(""))) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                            + etPhone.getText().toString().trim()));
                    /*上述两行代码为Android固有命令调用：直接拨打电话*/
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        /*Activity compat.check Self Permission:活动检查自我许可，即调用Manifest.xlm文件，检索是否获取到拨打电话权限,若为！=管理文件包：权限_授予，则返回。*/
                        return;
                    }
                    startActivity(intent);
                    /*intent:指明当前组件想要执行的动作，还可以用于不同组件之间传递数据。，次语句表示显示intent*/
                }
            }
        });
    }
}
