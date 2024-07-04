# -*- coding: utf-8 -*-
from flask import Flask, request, jsonify
from konlpy.tag import Okt
from gensim.models import FastText
from sklearn.metrics.pairwise import cosine_similarity
import numpy as np

app = Flask(__name__)
okt = Okt()

def recommendations(title, filtered_titles):

    # FastText model 학습
    model = FastText(vector_size=80, window=5, min_count=2, workers=-1)
    model.build_vocab(corpus_iterable=filtered_titles)
    model.train(corpus_iterable=filtered_titles, total_examples=len(filtered_titles), epochs=15)
   
    title_tokens = okt.morphs(title)
    title_vector = model.wv[title_tokens]
    title_vector_padded = np.pad(title_vector, ((0,1520 - len(title_vector)), (0,0)), mode='constant')
    title_vector_padded = title_vector_padded.reshape(1,-1)

    similarities = []
    for filtered_title in filtered_titles:

        filtered_title_tokens = okt.morphs(filtered_title)
        filtered_title_vector = model.wv[filtered_title_tokens]
        filtered_title_vector_padded = np.pad(filtered_title_vector, ((0, 1520 - len(filtered_title_vector)), (0, 0)), mode='constant')
        filtered_title_vector_padded = filtered_title_vector_padded.reshape(1,-1)

        similarity = cosine_similarity(title_vector_padded, filtered_title_vector_padded)[0][0]
        similarities.append(similarity)

    # 유사도가 높은 순서로 데이터 정렬
    sorted_indices = sorted(range(len(similarities)), key=lambda i: similarities[i], reverse=True)

    # 상위 n개 데이터 선택
    top_n = 5
    recommend = [filtered_titles[i] for i in sorted_indices[:top_n]]

    return recommend

@app.route('/')
def default():
    return "<p>This is Hi-sujung Recommendation Page</p>"

@app.route("/recommend/univ", methods=['POST'])
def univAct():
    try:
        data = request.json

        if not data or not isinstance(data, list):
            return jsonify({"error": "Invalid input format."}), 400
            
        title = data[0].get('title')
        if title is None:
            return jsonify({"error": "Notice title is required."}), 400
            
        filtered_title = [item['title'] for item in data[1:]]
        univ_activity_id = [item['univ_activity_id'] for item in data[1:]]

        recommend_list = recommendations(title, filtered_title)
        recommend_id_list = [univ_activity_id[filtered_title.index(recommend)] for recommend in recommend_list]
            
        response_dto = [{'univ_activity_id': rec_id, 'title': rec_title} for rec_id, rec_title in zip(recommend_id_list, recommend_list)]
        return jsonify(response_dto)
        
    except Exception as e:
        return jsonify({"error": str(e)}), 500

@app.route("/recommend/external", methods=['POST'])
def externalAct():
    try:
        data = request.json

        if not data or not isinstance(data, list):
            return jsonify({"error": "Invalid input format."}), 400
            
        title = data[0].get('title')
        if title is None:
            return jsonify({"error": "Notice title is required."}), 400
            
        filtered_title = [item['title'] for item in data[1:]]
        external_act_id = [item['external_act_id'] for item in data[1:]]

        recommend_list = recommendations(title, filtered_title)
        recommend_id_list = [external_act_id[filtered_title.index(recommend)] for recommend in recommend_list]
            
        response_dto = [{'external_act_id': rec_id, 'title': rec_title} for rec_id, rec_title in zip(recommend_id_list, recommend_list)]
        return jsonify(response_dto)
        
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True)
