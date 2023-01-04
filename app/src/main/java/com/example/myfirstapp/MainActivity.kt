package com.example.myfirstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
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

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}

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