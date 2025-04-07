import { Text, View, ScrollView, Image, Button, Alert } from 'react-native';
import React, { useEffect, useState } from 'react';
import { useLocalSearchParams } from 'expo-router';
import { useVideoPlayer, VideoView } from 'expo-video';

import LikeButton from '@/components/LikeButton';
import Comment from '@/components/Comment';

interface video {
  id: number,
  channelId: number,
  channelName: string,
  title: string,
  description: string,
  videoLink: string,
  posLikes: number,
  negLikes: number
}

export default function Watch() {
  const apiUrl = process.env.EXPO_PUBLIC_API_URL;
  const mediaUrl = process.env.EXPO_PUBLIC_MEDIA_URL;
  const { watch } = useLocalSearchParams();

  const [video, setVideo] = useState<video | null>(null);
  const [comments, setComments] = useState<any[]>([]);

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

    const fetchComments = async () => {
      try {
        let fetchData = await fetch(`${apiUrl}comment/${watch}`);
        const commnetData = await fetchData.json();
        setComments(commnetData);
      } catch (error) {
        console.error('Error fetching comments:', error);
      }
    }

    fetchVideo();
    fetchComments();
  }, []); 

  useEffect(() => {
    if (video) {
      console.log(`${mediaUrl}${video.videoLink}`);
    }
  }, [video]);

  const videoUrl = video ? `${mediaUrl}${video.videoLink}` : null;

  const player = useVideoPlayer(videoUrl, (player) => player.play());

  return (
    <View style={{ backgroundColor: '#25292e' }}>
      {video ? (
        videoUrl ? (
          <VideoView style={{ width: '100%', height: '25%' }} player={player} allowsFullscreen nativeControls />
        ) : (
          <Text>Video URL not available</Text>
        )
      ) : (
        <Text>Loading video...</Text>
      )}

      <ScrollView>
        <Text className='text-white text-2xl font-medium'>{video?.title}</Text>
        <View>
          <Image source={{ uri: mediaUrl }} className='m-2 rounded-md' style={{width: 50, height: 50}} />
        </View>
        <LikeButton posLike={video?.posLikes} negLike={video?.negLikes} />

        {comments.map((comment, index) => (
          <Comment
            key={comment.id}
            userId={comment.userId}
            userName={comment.userName}
            userPicLink={comment.userPicLink}
            comment={comment.comment}
          />
        ))}
      </ScrollView>
    </View>
  );
}