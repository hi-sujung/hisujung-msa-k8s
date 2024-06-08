addEventListener('fetch', event => {
    event.respondWith(handleRequest(event.request))
  })
  
  async function handleRequest(request) {
    const SLACK_WEBHOOK_URL = '{SLACK_WEBHOOK_URL}';
  
    try {
      // 요청의 정보를 수집
      const logData = {
        method: request.method,
        url: request.url,
        headers: Array.from(request.headers.entries()).reduce((acc, [key, value]) => {
          acc[key] = value;
          return acc;
        }, {}),
        body: await request.text(),
        timestamp: new Date().toISOString().replace(/T/, ' ').replace(/\..+/, '')
      };
   
      // Slack으로 로그 메시지 전송
      const slackResponse = await fetch(SLACK_WEBHOOK_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ 
          text: `!!! GKE 클러스터 변화 탐지 !!!\n발생 시간: ${logData.timestamp}\n로그 내용: ${logData.body}`
        })
      });
  
      if (!slackResponse.ok) {
        throw new Error(`Slack API error: ${slackResponse.statusText}`);
      }
  
      return new Response('Log forwarded to Slack', { status: 200 });
    } catch (error) {
      console.error('Error forwarding log to Slack:', error);
      return new Response('Error forwarding log to Slack', { status: 500 });
    }
  }
  


  