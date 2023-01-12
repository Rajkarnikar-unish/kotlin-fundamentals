package com.example.myfirstapp

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var receiver : AirplaneModeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiver = AirplaneModeChangeReceiver()

        //Dynamic Receiver
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}

/**
 * Drag and Drop
 *
 *
layoutTop.setOnDragListener(dragListener)
layoutBottom.setOnDragListener(dragListener)

dragView.setOnLongClickListener {
val clipText = "This is test data text"

val item = ClipData.Item(clipText)

val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)

val data = ClipData(clipText, mimeTypes, item)

val dragShadowBuilder = View.DragShadowBuilder(it)
it.startDragAndDrop(data, dragShadowBuilder, it, 0)

it.visibility = View.INVISIBLE

true
}
}

val dragListener = View.OnDragListener { view, event ->
when(event.action) {
DragEvent.ACTION_DRAG_STARTED -> {
event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
}
DragEvent.ACTION_DRAG_ENTERED -> {
view.invalidate()
true
}

DragEvent.ACTION_DRAG_LOCATION -> true
DragEvent.ACTION_DRAG_EXITED -> {
view.invalidate()
true
}

DragEvent.ACTION_DROP -> {
val item = event.clipData.getItemAt(0)
val dragData = item.text
Toast.makeText(this, dragData, Toast.LENGTH_SHORT).show()

view.invalidate()

val v = event.localState as View
val owner = v.parent as ViewGroup
owner.removeView(v)
val destination = view as LinearLayout
destination.addView(v)
v.visibility = View.VISIBLE
true
}
DragEvent.ACTION_DRAG_ENDED-> {
view.invalidate()
true
}
else -> false
}
 */

/**
 * Services
 *
 *
btnStart.setOnClickListener {
Intent(this, MyService::class.java).also{
startService(it)
serviceInfoTextView.text = "Service running"
}
}

btnStop.setOnClickListener {
Intent(this, MyService::class.java).also{
stopService(it)
serviceInfoTextView.text = "Service stopped"
}
}

btnSendData.setOnClickListener {
Intent(this, MyService::class.java).also {
val dataString = dataEditText.text.toString()
it.putExtra("EXTRA_DATA", dataString)
startService(it)
}
}
 *
 */

/**
 * Intent Services
 *
 * REFERS TO MYINTENTSERVICE File
 *
 * btnStart.setOnClickListener {
Intent(this, MyIntentService::class.java).also {
startService(it)
serviceInfoTextView.text = "Service running"
}
}

btnStop.setOnClickListener {
MyIntentService.stopService()
serviceInfoTextView.text = "Service stopped"
}

 */

/**
 * Notifications
 *
 * val CHANNEL_ID = "channelID"
val CHANNEL_NAME = "channelName"
val NOTIFICATION_ID = 0

 *
 * createNotificationChannel()

val intent = Intent(this, MainActivity::class.java)
val pendingIntent = TaskStackBuilder.create(this).run {
addNextIntentWithParentStack(intent)
getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
}

val notification = NotificationCompat.Builder(this, CHANNEL_ID)
.setContentTitle("TEST NOTIFICATION")
.setContentText("This is a test notification")
.setSmallIcon(R.drawable.ic_profile)
.setPriority(NotificationCompat.PRIORITY_HIGH)
.setContentIntent(pendingIntent)
.build()

val notificationManager = NotificationManagerCompat.from(this)

btnNotification.setOnClickListener {
notificationManager.notify(NOTIFICATION_ID, notification)
}
 *
 * fun createNotificationChannel() {
if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
NotificationManager.IMPORTANCE_DEFAULT).apply {
lightColor = Color.GREEN
enableLights(true)
}

val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

manager.createNotificationChannel(channel)
}
}
 */

/**
 * Shared Preferences
 *
 * val sharedPrefs = getSharedPreferences("myPref", Context.MODE_PRIVATE)

val editor = sharedPrefs.edit()

saveButton.setOnClickListener {
val name = nameEditText.text.toString()
val age = ageEditText.text.toString().toInt()
val isAdult = isAdultCheckBox.isChecked

editor.apply {
putString("name", name)
putInt("age", age)
putBoolean("isAdult", isAdult)

apply()
}

nameEditText.text.clear()
ageEditText.text.clear()
isAdultCheckBox.isChecked = false

Toast.makeText(this, "Your details have been saved.", Toast.LENGTH_SHORT).show()
}

clearButton.setOnClickListener {
nameEditText.text.clear()
ageEditText.text.clear()
isAdultCheckBox.isChecked = false
}

loadButton.setOnClickListener {
val name = sharedPrefs.getString("name", null)
val age = sharedPrefs.getInt("age", 0)
val isAdult = sharedPrefs.getBoolean("isAdult", false)

nameEditText.setText(name)
ageEditText.setText(age.toString())
isAdultCheckBox.isChecked = isAdult

Toast.makeText(this, "Successfully loaded the data.", Toast.LENGTH_SHORT).show()
}
 *
 */

/**
 * Navigation Drawer
 *
 * lateinit var toggle: ActionBarDrawerToggle
 *
 * toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

drawerLayout.addDrawerListener(toggle)
toggle.syncState()

supportActionBar?.setDisplayHomeAsUpEnabled(true)

navView.setNavigationItemSelectedListener {
when(it.itemId) {
R.id.miItem1 -> Toast.makeText(applicationContext, "Clicked Item 1", Toast.LENGTH_SHORT).show()
R.id.miItem2 -> Toast.makeText(applicationContext, "Clicked Item 2", Toast.LENGTH_SHORT).show()
R.id.miItem3 -> Toast.makeText(applicationContext, "Clicked Item 3", Toast.LENGTH_SHORT).show()
}
true
}
 *
 * override fun onOptionsItemSelected(item: MenuItem): Boolean {
if (toggle.onOptionsItemSelected(item)) {
return true
}
return super.onOptionsItemSelected(item)
}
 */

/**
 * TAB LAYOUT WITH VIEW PAGER 2
 *
 * val images = listOf(
R.drawable.image1,
R.drawable.image2,
R.drawable.image3,
R.drawable.number,
)

val adapter = ViewPagerAdapter(images)
viewPager.adapter = adapter

TabLayoutMediator(tabLayout, viewPager) { tab, position ->
tab.text = "Tab ${position + 1}"
}.attach()

tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
override fun onTabSelected(tab: TabLayout.Tab?) {
Toast.makeText(this@MainActivity, "Selected ${tab?.text}", Toast.LENGTH_SHORT).show()
}

override fun onTabUnselected(tab: TabLayout.Tab?) {
Toast.makeText(this@MainActivity, "Unselected ${tab?.text}", Toast.LENGTH_SHORT).show()
}

override fun onTabReselected(tab: TabLayout.Tab?) {
Toast.makeText(this@MainActivity, "Reselected ${tab?.text}", Toast.LENGTH_SHORT).show()

}
})
 */

/**
 *
 * View Pager 2 WITH RECYCLER VIEW
 *
 *
val images = listOf(
R.drawable.image1,
R.drawable.image2,
R.drawable.image3,
R.drawable.number,
)

val adapter = ViewPagerAdapter(images)

viewPager.adapter = adapter
//        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
//        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
viewPager.beginFakeDrag()
viewPager.fakeDragBy(-10f)
viewPager.endFakeDrag()
 *
 */

/**
 * BOTTOM NAVIGATION VIEW WITH FRAGMENTS
 *
 * val firstFragment = FirstFragment()
val secondFragment = SecondFragment()
val thirdFragment = ThirdFragment()

setCurrentFragment(firstFragment)

//setOnItemSelectedListener
//NavigationItemSelectedListener deprecated
bottomNavigationView.setOnNavigationItemSelectedListener {
when(it.itemId) {
R.id.miHome -> setCurrentFragment(firstFragment)
R.id.miMessages -> setCurrentFragment(secondFragment)
R.id.miProfile -> setCurrentFragment(thirdFragment)
}
true
}

bottomNavigationView.getBadge(R.id.miMessages)?.apply {
number = 10
isVisible = true
}
 *
 *private fun setCurrentFragment(fragment: Fragment) =
supportFragmentManager.beginTransaction().apply {
replace(R.id.flFragment, fragment)
commit()
}
 *
 */

/** FRAGMENTS
 *
 *
val firstFragment = FirstFragment()
val secondFragment = SecondFragment()

supportFragmentManager.beginTransaction().apply {
replace(R.id.flFragment, firstFragment)
commit()
}

btnFragment1.setOnClickListener {
supportFragmentManager.beginTransaction().apply {
replace(R.id.flFragment, firstFragment)
addToBackStack(null)
commit()
}
}

btnFragment2.setOnClickListener {
supportFragmentManager.beginTransaction().apply {
replace(R.id.flFragment, secondFragment)
addToBackStack(null)
commit()
}
}
 */

/**
 * RECYCLER VIEW WITH ADAPTER
 *
 *
 *var todoList = mutableListOf(
Todo("Add project to github", false),
Todo("Keep working in kotlin", false),
Todo("RecyclerView", true),
Todo("Take a bath", false),
Todo("Watch anime", false),

)

val adapter = TodoAdapter(todoList)

rvTodos.adapter = adapter
rvTodos.layoutManager = LinearLayoutManager(this)

btnAddTodo.setOnClickListener {
val title = etTodo.text.toString()

val todo = Todo(title, false)
todoList.add(todo)
adapter.notifyItemInserted(todoList.size - 1)

etTodo.text.clear()
}
 *
 */


/**
 * Spinner Widgets (Dropdown )
 * //List of data for dropdown without use of string xml
val customList = listOf("First", "Second", "Third", "Fourth")

val adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, customList)

spMonths.adapter = adapter

spMonths.onItemSelectedListener = object : OnItemSelectedListener {
override fun onItemSelected(
adapterView: AdapterView<*>?,
view: View?,
position: Int,
id: Long
) {
Toast.makeText(
this@MainActivity,
"You selected ${adapterView?.getItemAtPosition(position).toString()}",
Toast.LENGTH_LONG).show()
}

override fun onNothingSelected(parent: AdapterView<*>?) {
}
}
 */


/**
 *
 * ALERT DIALOGS
 * val addContactDialog = AlertDialog.Builder(this)
.setTitle("Add Contact")
.setMessage("Do you want to add Mr.Unish to your contact")
.setIcon(R.drawable.ic_add_contact_2)
.setPositiveButton("Yes") { _, _ ->
Toast.makeText(this, "You have added this person.", Toast.LENGTH_SHORT).show()
}
.setNegativeButton("No") {_, _ ->
Toast.makeText(this, "You didn't added this person.", Toast.LENGTH_SHORT).show()
}.create()

btnDialog1.setOnClickListener {
addContactDialog.show()
}

val options = arrayOf("First Item", "Second Item", "Third Item")

val singleChoiceDialog = AlertDialog.Builder(this)
.setTitle("Choose One Option")
//            .setSingleChoiceItems(options, 0 ) {dialogInterface, i ->
//                Toast.makeText(this, "You chose ${options[i]}", Toast.LENGTH_SHORT).show()
//            }
.setMultiChoiceItems(options, booleanArrayOf(false, false, false)){_, i, b ->
if(b) {
Toast.makeText(this, "You checked options ${options[i]} .", Toast.LENGTH_SHORT).show()
}
Toast.makeText(this, "You unchecked options ${options[i]} .", Toast.LENGTH_SHORT).show()
}
.setPositiveButton("Accept") { _, _ ->
Toast.makeText(this, "You chose an option .", Toast.LENGTH_SHORT).show()
}
.setNegativeButton("Decline") {_, _ ->
Toast.makeText(this, "You didn't choose any option.", Toast.LENGTH_SHORT).show()
}.create()

btnDialog3.setOnClickListener {
singleChoiceDialog.show()
}
 */

/**
 *
 * Toolbar Menus
 *
 * override fun onCreateOptionsMenu(menu: Menu?): Boolean {
 * menuInflater.inflate(R.menu.app_bar_menu, menu)
 * return true
 * }
 *
 * override fun onOptionsItemSelected(item: MenuItem): Boolean {
 * when(item.itemId) {
 * R.id.miAddContact -> Toast.makeText(this, "You clicked on Add Contact", Toast.LENGTH_LONG).show()
 * R.id.miFavorites -> Toast.makeText(this, "You clicked on Favorites", Toast.LENGTH_LONG).show()
 * R.id.miSettings -> Toast.makeText(this, "You clicked on Settings", Toast.LENGTH_LONG).show()
 * R.id.miFeedback -> Toast.makeText(this, "You clicked on Feedback", Toast.LENGTH_LONG).show()
 * R.id.miClose -> finish()
 * }
 * return true
 * }
 */


/**
 * IMPLICIT INTENTS
 *
 * btnTakePhoto.setOnClickListener {
Intent(Intent.ACTION_GET_CONTENT).also {
it.type = "image/*"
startActivityForResult(it, 0)
}

}
 *
 * override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
super.onActivityResult(requestCode, resultCode, data)

if(resultCode == Activity.RESULT_OK && requestCode == 0) {
val uri = data?.data
ivPhoto.setImageURI(uri)
}
}
*/


////////////////////////////////////////////////////////////////////////////////
/**
 * LOCATION AND INTERNET PERMISSIONS
 *

private fun hasWriteExternalStoragePermissions() = ActivityCompat.checkSelfPermission(
this,
Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

private fun hasLocationForegroundPermission() = ActivityCompat.checkSelfPermission(
this,
Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

private fun hasLocationBackgroundPermission() = ActivityCompat.checkSelfPermission(
this,
Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED

private fun requestPermissions() {
var permissionToRequest = mutableListOf<String>()

if(!hasWriteExternalStoragePermissions()) {
permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
}
if(!hasLocationForegroundPermission()) {
permissionToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
}
if(!hasLocationBackgroundPermission()) {
permissionToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
}

if(permissionToRequest.isNotEmpty()) {
ActivityCompat.requestPermissions(this, permissionToRequest.toTypedArray(), 0)
}
}

override fun onRequestPermissionsResult(
requestCode: Int,
permissions: Array<out String>,
grantResults: IntArray
) {
super.onRequestPermissionsResult(requestCode, permissions, grantResults)

if(requestCode == 0 && grantResults.isNotEmpty()) {
for (i in grantResults.indices) {
if (grantResults i] == PackageManager.PERMISSION_GRANTED) {
Log.d("PermissionRequest", "${permissions i]} granted.")
}
}

}
}
*/


///////////////////////////////////////////////////////
/**
 * Passing data using intents from activities
 *
 *
 * btnApply.setOnClickListener {
 * val name = etName.text.toString()
 * val age = etAge.text.toString().toInt()
 * val country = etCountry.text.toString()
 *
 * val person = Person(name, age, country)
 *
 * Intent(this, SecondActivity::class.java).also {
 * it.putExtra("EXTRA_PERSON", person)
 * startActivity(it)
 * }
 * }
*/

///////////////////////////////////////////
/**
 * INTENTS
 *
 * btnOpenActivity.setOnClickListener {
 * Intent(this, SecondActivity::class.java).also {
 * startActivity(it)
 * }
 * }
*/

/////////////////////////////////////////////////////////////////
/**
 *
 * TOASTS
btnToast.setOnClickListener {

//Custom Toast
Toast(this).apply {
duration = Toast.LENGTH_LONG
view = layoutInflater.inflate(R.layout.custom_toast, clToast)
show()
}


Toast.makeText(applicationContext, "THIS IS A TOAST MESSAGE", Toast.LENGTH_LONG).show()

btnOrder.setOnClickListener {
val checkedMeatRadioBtnID = rgMeat.checkedRadioButtonId
val meat = findViewById<RadioButton>(checkedMeatRadioBtnID)

val cheese = cbCheese.isChecked
val onions = cbOnions.isChecked
val salad = cbSalad.isChecked

val orderDetail =
"Your burger will consist: " +
"\n ${meat.text}" +
(if (cheese) "\n Cheese" else "") +
(if (onions) "\n Onions" else "") +
(if (salad) "\n Salad" else "")

tvOrder.text = orderDetail
}
 */