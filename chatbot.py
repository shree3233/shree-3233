pip install scikit-learn nltk

# offline_chatbot.py

import nltk
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

nltk.download('punkt')  # First time only
from nltk.tokenize import sent_tokenize

# Knowledge base (expand this as needed)
faq_data = """
Hi! How can I assist you?
Our customer support is available 24/7.
You can return a product within 30 days of delivery.
You can track your order from the 'My Orders' page.
To speak to a human, please call our helpline at 1800-123-456.
Refunds are processed within 5-7 business days.
Please provide your order ID to help us assist you better.
You can cancel your order from the orders section if it's not yet shipped.
"""

# Preprocess
sentences = sent_tokenize(faq_data.lower())

def get_response(user_input):
    user_input = user_input.lower()
    all_sentences = sentences + [user_input]

    vectorizer = TfidfVectorizer()
    vectors = vectorizer.fit_transform(all_sentences)
    similarity = cosine_similarity(vectors[-1], vectors[:-1])
    index = np.argmax(similarity)

    score = similarity[0, index]
    if score < 0.2:
        return "I'm sorry, I couldn't understand that. Could you rephrase?"
    return sentences[index]

def main():
    print("Smart Offline Customer Service Chatbot (type 'bye' to quit)\n")

    while True:
        user_input = input("You: ")
        if user_input.lower() == "bye":
            print("Bot: Thank you for chatting. Goodbye!")
            break

        response = get_response(user_input)
        print("Bot:", response)

if __name__ == "__main__":
    main()
