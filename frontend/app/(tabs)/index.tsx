import React, { useEffect, useState } from 'react';
import { Text, View, ScrollView, Platform } from 'react-native';
import { Link } from 'expo-router';

import VideoCard from '@/components/VideoCard';

export default function Index() {
  const apiUrl = process.env.EXPO_PUBLIC_API_URL;
  const mediaUrl = process.env.EXPO_PUBLIC_MEDIA_URL;

  const [videoData, setVideoData] = useState<any[]>([]);

  // Fetch videos data when component mounts
  useEffect(() => {
    const fetchVideos = async () => {
      try {
        let fetchData = await fetch(`${apiUrl}video/videos`);
        const videos = await fetchData.json();
        setVideoData(videos); // Set the fetched data to the state
      } catch (error) {
        console.error('Error fetching videos:', error);
      }
    };

    fetchVideos();
  }, []); 

  return (
    <View className='flex flex-row flex-grow' style={{ backgroundColor: '#25292e' }}>
      {Platform.OS === 'web' &&
        <View>
          <Text>Menue</Text>
        </View>
      }
      <ScrollView className={`flex ${Platform.OS === 'web' ? 'flex-row' : 'flex-col'} justify-items-center w-full h-screen`}>
        {videoData.map((video, index) => (
          <VideoCard
            key={video.id}
            id={video.id}
            name={video.title}
            thumbnail={mediaUrl + video.thumbnailLink}
            channel={video.channelname}
            views={video.views}
            color='dark'
          />
        ))}
      </ScrollView>
    </View>
  );
}