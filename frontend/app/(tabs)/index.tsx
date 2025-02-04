import { Text, View, ScrollView, Platform } from 'react-native';
import { Link } from 'expo-router';

import VideoCard from '@/components/VideoCard';

export default function Index() {
  return (
    <View className='flex flex-row flex-grow' style={{ backgroundColor: '#25292e' }}>
      {Platform.OS === 'web' &&
        <View>
          <Text>Menue</Text>
        </View>
      }
      <ScrollView className={`flex ${Platform.OS === 'web' ? 'flex-row' : 'flex-col'} justify-items-center`} style={{ width: '100%' }}>
        <VideoCard name='Test title' channel='Test channel' views='1.6 mln' color='dark'></VideoCard>
        <VideoCard name='Test title' channel='Test channel' views='1.6 mln' color='dark'></VideoCard>
        <VideoCard name='Test title' channel='Test channel' views='1.6 mln' color='dark'></VideoCard>
        <VideoCard name='Test title' channel='Test channel' views='1.6 mln' color='dark'></VideoCard>
      </ScrollView>
    </View>
  );
}