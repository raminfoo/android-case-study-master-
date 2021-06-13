package com.target.targetcasestudy.utils

object Constants {
    const val INTENT_DESCRIPTION = "intent_description"
    const val INTENT_CARD_NUMBER = "card_number"

    fun wrapWithHtml(content: String): String {
        return "<html><body ><p align=\"justify\" style=\"font-size: 20px; " +
                "word-wrap: break-word; white-space: pre-wrap;\">" + content + "</p> " + "</body></style></html>";
    }
}