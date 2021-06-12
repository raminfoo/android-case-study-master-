package com.target.targetcasestudy.utils

object Constants {
    const val INTENT_DESCRIPTION = "intent_description"
    const val INTENT_CARD_NUMBER = "card_number"

    fun wrapWithHtml(content: String): String {
        return "<html><head> <pre style=\"word-wrap: break-word; white-space: pre-wrap; font-size:16; -webkit-user-select: none;\" /></head><body>$content</body></html>";
    }
}