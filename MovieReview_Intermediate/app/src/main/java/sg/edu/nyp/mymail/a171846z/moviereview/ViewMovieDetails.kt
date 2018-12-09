package sg.edu.nyp.mymail.a171846z.moviereview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_movie_details.*
import kotlinx.android.synthetic.main.activity_main.*

class ViewMovieDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie_details)

        var venom = MovieEntity();
        venom.mvTitle = "Venom"
        venom.mvLanguage =  "English"
        venom.mvOverview = "When Eddie Brock acquires the powers of a symbiote, he will have to release his alter ego Venom to save his life."
        venom.mvReleaseDate = "03-10-2018"
        venom.mvSuitableForChild = true
        tvTitle.text = venom.mvTitle.toString()
        tvLanguage.text = venom.mvLanguage.toString()
        tvOverview.text = venom.mvOverview.toString()
        tvReleaseDate.text = venom.mvReleaseDate.toString()
        if (venom.mvSuitableForChild == true){
            tvSuitableForChildrenBelow13.text = "Yes"
        } else {
            tvSuitableForChildrenBelow13.text = "No"
        }
    }

    class MovieEntity() {
        var mvTitle: String = ""
        var mvOverview: String = ""
        var mvLanguage: String = ""
        var mvReleaseDate: String = ""
        var mvSuitableForChild: Boolean = false
        init {
            this.mvTitle
            this.mvOverview
            this.mvLanguage
            this.mvReleaseDate
            this.mvSuitableForChild
        }
    }
}
