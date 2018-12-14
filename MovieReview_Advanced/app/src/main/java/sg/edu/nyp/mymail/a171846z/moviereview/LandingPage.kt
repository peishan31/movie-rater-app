package sg.edu.nyp.mymail.a171846z.moviereview

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.activity_landing_page.*
import android.R.array
import android.widget.AdapterView
import android.view.ContextMenu.ContextMenuInfo
import android.view.ContextMenu
import android.view.MenuInflater
import android.widget.Toast
import android.widget.AdapterView.OnItemLongClickListener

class LandingPage : AppCompatActivity() {

    companion object {
        var listMovies = ArrayList<MovieEntity>()
        var moviePosition: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        if (listMovies.isEmpty()){
            landing_lvNotes.visibility = View.GONE
        } else {
            // Listing out all the movies
            landing_lvNotes.visibility = View.VISIBLE
            var moviesAdapter = MoviesAdapter(this, listMovies)
            landing_lvNotes.adapter = moviesAdapter
            landing_lvNotes.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
               // Toast.makeText(this, "Click on ${listMovies[position].mvTitle} ${listMovies[position].mvOverview} ${position}" , Toast.LENGTH_SHORT).show()
                moviePosition = position
                var myIntent = Intent(this, ViewMovieDetails::class.java)
                startActivity(myIntent)
            }
            registerForContextMenu(landing_lvNotes)

            val listview = findViewById(R.id.landing_lvNotes) as ListView
            listview.setLongClickable(true);
            landing_lvNotes.setOnItemLongClickListener(object : AdapterView.OnItemLongClickListener {

                override fun onItemLongClick(
                    arg0: AdapterView<*>, v: View,
                    position: Int, arg3: Long
                ): Boolean {
                    moviePosition = position
                    //Toast.makeText(this@LandingPage, "${listview.getItemAtPosition(position).toString()} ${position}", Toast.LENGTH_LONG).show()
                    return false
                }
            })
        }
    }

    inner class MoviesAdapter : BaseAdapter {

        private var notesList = ArrayList<MovieEntity>()
        private var context: Context? = null

        constructor(context: Context, notesList: ArrayList<MovieEntity>) : super() {
            this.notesList = notesList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.adapter_view_layout, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            vh.landingTitle.text = notesList[position].mvTitle

            return view
        }

        override fun getItem(position: Int): Any {
            return notesList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return notesList.size
        }
    }
    private class ViewHolder(view: View?) {
        val landingTitle: TextView

        init {
            this.landingTitle = view?.findViewById(R.id.landing_title) as TextView
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.landing_lvNotes) {
            menu?.add(1, 1001, 1, "Edit");
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

            if (item?.itemId == 1001){
                // to another page
                var myIntent = Intent(this, EditMovie::class.java)
                startActivity(myIntent)
            }

        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.landingmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.landing_miAddMovie){
            // to another page
            var myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}
