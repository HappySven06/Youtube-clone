import { Text, View } from 'react-native';
import React, { useEffect, useState } from 'react';
import { useLocalSearchParams } from 'expo-router';
import { Video } from 'expo-av';

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

  return (
    <View>
      {video ? (
        <Video
          source={{ uri: `${mediaUrl}${video.video.link}` }}
          shouldPlay
          useNativeControls
          style={{ width: '100%', height: 300 }}
        />
      ) : (
        <Text>Loading video...</Text>
      )}
    </View>
  );
}