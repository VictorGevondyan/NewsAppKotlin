package am.victor.newsappkotlin.activities

import am.victor.newsapp.models.NewsItem
import am.victor.newsappkotlin.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class NewsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val titleTextView = findViewById<TextView>(R.id.title)
        val contentTextView = findViewById<TextView>(R.id.content)

        val incomingIntent = intent
        val titleString = incomingIntent.getStringExtra(EXTRA_TITLE)
        val contentString = incomingIntent.getStringExtra(EXTRA_TEXT)

        titleTextView.text = titleString
        contentTextView.text = contentString
    }

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_TEXT = "content"

        fun newIntent(context: Context, newsItem: NewsItem): Intent {
            val detailIntent = Intent(context, NewsDetailsActivity::class.java)

            detailIntent.putExtra(EXTRA_TITLE, newsItem.title)
            detailIntent.putExtra(EXTRA_TEXT, newsItem.content)

            return detailIntent
        }
    }

}
