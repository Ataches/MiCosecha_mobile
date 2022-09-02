package com.mobile.micosecha.ui.base

import android.os.Bundle

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.micosecha.ui.adapter.MessageAdapter
import com.mobile.micosecha.util.BotResponse
import com.mobile.micosecha.util.Constants.OPEN_INSTA
import com.mobile.micosecha.util.Constants.OPEN_SEARCH
import kotlinx.coroutines.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mobile.micosecha.R
import com.mobile.micosecha.data.api.ChatMessage
import com.mobile.micosecha.data.api.ChatMessageResponse
import com.mobile.micosecha.data.api.asMessage
import com.mobile.micosecha.databinding.FragmentChatBinding
import com.mobile.micosecha.util.Constants.OPEN_GRAPH
import com.mobile.micosecha.util.Constants.RECEIVE_ID
import com.mobile.micosecha.util.Constants.SEND_ID


class ChatFragment : Fragment() {

    var messagesList = mutableListOf<ChatMessage>()

    private lateinit var adapter: MessageAdapter
    private lateinit var binding: FragmentChatBinding
    private val botList = listOf("Grace", "Lenny", "QuickBot")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        binding = FragmentChatBinding.inflate(inflater, container, false)

        recyclerView()

        clickEvents()

        val random = (0..2).random()
        customBotMessage("Bienvenido!")
        customBotMessage("Te escribe ${botList[random]},")
        customBotMessage("En que te puedo ayudar?")

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun clickEvents() {

        //Send a message
        binding.btnSend.setOnClickListener {
            sendMessage(it)
        }

        //Scroll back to correct position when user clicks on text view
        binding.etMessage.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessageAdapter()
        binding.rvMessages.adapter = adapter
        binding.rvMessages.layoutManager = LinearLayoutManager(context)

    }

    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendMessage(view: View) {
        val message = binding.etMessage.text.toString()

        if (message.isNotEmpty()) {
            val chatMessage = ChatMessage(message, SEND_ID)
            //Adds it to our local list
            messagesList.add(chatMessage)
            binding.etMessage.setText("")

            adapter.insertMessage(chatMessage)
            binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

            botResponse(view, message)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun botResponse(view: View, message: String) {

        GlobalScope.launch {

            withContext(Dispatchers.Main) {
                //Gets the response
                val botResponse: ChatMessageResponse = BotResponse().basicResponses(message)
                val response: ChatMessage = botResponse.asMessage()

                //Adds it to our local list
                messagesList.add(response)

                //Inserts our message into the adapter
                adapter.insertMessage(response)

                //Scrolls us to the position of the latest message
                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

                //Starts Google
                when (response.message) {
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String = message.substringAfterLast("google")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                    OPEN_GRAPH -> {
                        view.findNavController().navigate(R.id.action_chatFragment_to_nav_bright)
                    }
                    OPEN_INSTA -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.instagram.com/")
                        startActivity(site)
                    }
                }
            }
        }
    }

    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val chatMessage = ChatMessage(message, RECEIVE_ID)
                messagesList.add(chatMessage)
                adapter.insertMessage(chatMessage)

                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}