package com.pikkapa.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.pikkapa.R
import com.pikkapa.databinding.ActivityInformationBinding
import com.pikkapa.domain.InformationEntity
import com.pikkapa.view.adapter.InformationItemAdapter

class InformationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInformationBinding
    private var informations : ArrayList<InformationEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_information)

        setToolbar()
        setAllView()
        setAllOnClick()

        informations.add(InformationEntity("judul 1", "ini adalah foto orang peace di pantai", "gambar 1"))
        informations.add(InformationEntity("judul 2","ini adalah foto orang peace di pantai", "https://drive.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51"))
        informations.add(InformationEntity("judul 3", "ini adalah foto orang peace di pantai", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51"))
        informations.add(InformationEntity("judul 4", "ini adalah foto orang peace di pantai", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51"))
        informations.add(InformationEntity("judul 5","ini adalah foto orang peace di pantai", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51", "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"fadedtab.css\" />\n" +
                "<title>Faded Tab by Bryant Smith</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\t\t\n" +
                "        <div id=\"header\">\n" +
                "        \t<div class=\"logo\"></div>\n" +
                "            <h1>Faded Tab</h1>\n" +
                "\t\t\t<h2>This template brings success, I promise.</h2>\n" +
                "        </div>\n" +
                "<div id=\"bottomContent\">\n" +
                "\t<div class=\"navColumn\"> \n" +
                "    \t<div class=\"overview\">\n" +
                "        \tThis is where you place either you company slogan, motto, or any keyword rich information.   This information will be the first 'content' that will be read by the search engines, so try to make it good ;-)   Also keep in mind that this will be true for every page, so it might be a good idea to change this area for each page.  You might say that this section is strategically placed for that purpose, so use it carefully.        </div>\n" +
                "    \t<div class=\"link\"><a href=\"http://www.bryantsmith.com\">Home</a></div>\n" +
                "        <div class=\"link\"><a href=\"http://www.bryantsmith.com\">About Us</a></div>\n" +
                "        <div class=\"link\"><a href=\"http://www.bryantsmith.com\">Services</a></div>\n" +
                "        <div class=\"link\"><a href=\"http://www.bryantsmith.com\">Portfolio</a></div>\n" +
                "        <div class=\"link\"><a href=\"http://www.bryantsmith.com\">Web Programming</a></div>        \n" +
                "        <div class=\"link\"><a href=\"http://www.bryantsmith.com\">Search Engine Optimization</a></div>\n" +
                "        <div class=\"link\"><a href=\"http://www.bryantsmith.com\">Contact Us</a></div>\n" +
                "        \n" +
                "<div id=\"navBottom\"></div>\n" +
                "    </div>\n" +
                "\t<div class=\"contentColumn\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t<!--Start Content Box (use one of the simple ones below -->\n" +
                "  \t<!--  To make a new content box, copy and paste from the Start to End, and paste it below this one.. you can create as many as you like. -->\n" +
                "      <div class=\"contentTop\"></div>\n" +
                "      <div class=\"contentMain\"> \n" +
                "            <h1>The title for your content</h1>\n" +
                "                <p>You can include all of your content in one of these boxes, spread it out between many boxes, list one title per box, or many titles per box.   Feel free to use this template anywhere you'd like, but please leave the link back to me.  </p>\n" +
                "                <p>\n" +
                "            In order to include two titles in one of these content boxes all your have to do is create another H1 tag; and follow it with the content you wanted like so (note: if you are using comments, also include the comments div.) </p>\n" +
                "            \n" +
                "                        <div class=\"comments\"><a href=\"#\">Comments (1)</a></div>\n" +
                "                        <br />\n" +
                "\n" +
                "                        \n" +
                "            <h1>A Second H1 Tag</h1>\n" +
                "            <p>You can include all of your content in one of these boxes, spread it out between many boxes, list one title per box, or many titles per box.   Feel free to use this template anywhere you'd like, but please leave the link back to me.  </p>\n" +
                "            \n" +
                "                                    <div class=\"comments\"><a href=\"#\">Comments (5)</a></div>\n" +
                "                                    <br />\n" +
                "\n" +
                "                        \n" +
                "            <h1>A Third H1 Tag</h1>\n" +
                "            <p>You can include all of your content in one of these boxes, spread it out between many boxes, list one title per box, or many titles per box.   Feel free to use this template anywhere you'd like, but please leave the link back to me.  </p>\n" +
                "                \n" +
                "                \n" +
                "            <div class=\"comments\"><a href=\"#\">Comments (34)</a></div>\n" +
                "     </div>\n" +
                "     <div class=\"contentBottom\"></div>\n" +
                "   \t<!--End Content Box -->\n" +
                "    \n" +
                "  \n" +
                "    \t<!--Start Content Box -->\n" +
                "  \t<!--  To make a new content box, copy and paste from the Start to End, and paste it below this one.. you can create as many as you like. -->\n" +
                "      <div class=\"contentTop\"></div>\n" +
                "      <div class=\"contentMain\"> \n" +
                "            <h1>The title for your content</h1>\n" +
                "                You can include all of your content in one of these boxes, spread it out between many boxes, list one title per box, or many titles per box.   \n" +
                "            <div class=\"comments\"><a href=\"#\">Comments (42)</a></div>\n" +
                "     </div>\n" +
                "     <div class=\"contentBottom\"></div>\n" +
                "   \t<!--End Content Box -->\n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    <!--Start Content Box -->\n" +
                "  \t<!--  To make a new content box, copy and paste from the Start to End, and paste it below this one.. you can create as many as you like. -->\n" +
                "      <div class=\"contentTop\"></div>\n" +
                "      <div class=\"contentMain\"> \n" +
                "            <h1>Copy this box</h1>\n" +
                "                Enter your content here   \n" +
                "            <div class=\"comments\"><a href=\"#\">Comments (16)</a></div>   \n" +
                "            <!-- feel free to delete this line if you don't use comments -->\n" +
                "     </div>\n" +
                "     <div class=\"contentBottom\"></div>\n" +
                "   \t<!--End Content Box -->\n" +
                "    \n" +
                "    \n" +
                "\n" +
                "\n" +
                "\t<!-- PLEASE LEAVE THIS AT THE END OF YOUR LAST BOX -->\n" +
                "    <h4><a href=\"http://www.aszx.net\">web development</a> by <a href=\"http://www.bryantsmith.com\">bryant smith</a></h4>\n" +
                "    <!-- PLEASE LEAVE THIS AT THE END OF YOUR LAST BOX -->\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t</div>\n" +
                "\n" +
                "</div>\n" +
                "             \n" +
                "</body>\n" +
                "</html>"))

        binding.rvItemInformation.layoutManager = LinearLayoutManager(this)
        binding.rvItemInformation.adapter = InformationItemAdapter(informations, true) {
            val myIntent = Intent(this, InformationDetailActivity::class.java)
            myIntent.putExtra("title", it.title)
            myIntent.putExtra("html", it.html)
            this.startActivity(myIntent)
        }
    }

    fun setToolbar() {
        binding.toolbar.tvTitle.text = "INFORMASI"

        binding.toolbar.ivMenu.setOnClickListener{
            val myIntent = Intent(this, MenuActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.toolbar.ivInfo.setOnClickListener {
            val myIntent = Intent(this, AboutMeActivity::class.java)
            this.startActivity(myIntent)
        }
    }

    fun setAllView() {

        binding.searchBarText.addTextChangedListener {
            if (!it.toString().equals("")) {
                var filtered = informations.filter{info -> info.title.toUpperCase().contains(it.toString().toUpperCase()) }
                binding.rvItemInformation.adapter = InformationItemAdapter(filtered as ArrayList<InformationEntity>, true) {
                    val myIntent = Intent(this, InformationDetailActivity::class.java)
                    myIntent.putExtra("title", it.title)
                    myIntent.putExtra("html", it.html)
                    this.startActivity(myIntent)
                }
            } else {
                binding.rvItemInformation.adapter = InformationItemAdapter(informations, true) {
                    val myIntent = Intent(this, InformationDetailActivity::class.java)
                    myIntent.putExtra("title", it.title)
                    myIntent.putExtra("html", it.html)
                    this.startActivity(myIntent)
                }
            }
        }

    }

    fun setAllOnClick() {

    }
}