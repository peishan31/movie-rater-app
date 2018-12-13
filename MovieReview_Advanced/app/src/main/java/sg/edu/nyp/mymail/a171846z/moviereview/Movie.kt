package sg.edu.nyp.mymail.a171846z.moviereview

class MovieEntity(var mvTitle: String, var mvOverview: String, var mvLanguage: String, var mvReleaseDate: String,
                  var mvSuitableForChild: String) {
    /*var mvTitle: String = ""
    var mvOverview: String = ""
    var mvLanguage: String = ""
    var mvReleaseDate: String = ""
    var mvSuitableForChild: String = ""*/
    //var mvReason: String = ""
    var star: Float = 0.toFloat()
    var review: String = ""
    init {
        this.mvTitle
        this.mvOverview
        this.mvLanguage
        this.mvReleaseDate
        this.mvSuitableForChild
        //this.mvReason
    }
}