package am.victor.newsappkotlin.activities

import am.victor.newsapp.fragments.dummy.DummyContent
import am.victor.newsapp.models.NewsItem
import am.victor.newsappkotlin.R
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class NewsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
    }

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_TEXT = "text"

        fun newIntent(context: Context, newsItem: DummyContent.DummyItem): Intent {
            val detailIntent = Intent(context, NewsDetailsActivity::class.java)

            detailIntent.putExtra(EXTRA_TITLE, newsItem.id)
            detailIntent.putExtra(EXTRA_TEXT, newsItem.details)

            return detailIntent
        }
    }

}
