import { Text, View, ScrollView } from 'react-native';
import React, { useEffect, useState } from 'react';
import { useLocalSearchParams } from 'expo-router';
import { useVideoPlayer, VideoView } from 'expo-video';

interface videoObject {
  access: boolean,
  id: number,
  link: string,
  media: number,
  type: string,
  userId: number,
  video: number
}

interface videoInterface {
  commentList: Array<object>,
  description: string,
  historyList: Array<object>
  id: number,
  ratingList: Array<object>, 
  thumbnail: object,
  title: string,
  userId: number,
  video: videoObject
}

export default function Watch() {
  const apiUrl = process.env.EXPO_PUBLIC_API_URL;
  const mediaUrl = process.env.EXPO_PUBLIC_MEDIA_URL;
  const { watch } = useLocalSearchParams();

  const [video, setVideo] = useState<videoInterface | null>(null);

  useEffect(() => {
    const fetchVideo = async () => {
      try {
        let fetchData = await fetch(`${apiUrl}video/${watch}`);
        const videoData = await fetchData.json();
        setVideo(videoData);
      } catch (error) {
        console.error('Error fetching videos:', error);
      }
    };

    fetchVideo();
  }, []); 

  useEffect(() => {
    if (video) {
      console.log(`${mediaUrl}${video.video.link}`);
    }
  }, [video]);

  const videoUrl = video ? `${mediaUrl}${video.video.link}` : null;

  const player = useVideoPlayer(videoUrl, (player) => player.play());

  return (
    <View className='flex'>
      {video ? (
        videoUrl ? (
          <VideoView style={{ width: '100%', height: '55%' }} player={player} allowsFullscreen nativeControls />
        ) : (
          <Text>Video URL not available</Text>
        )
      ) : (
        <Text>Loading video...</Text>
      )}
    </View>
  );
}