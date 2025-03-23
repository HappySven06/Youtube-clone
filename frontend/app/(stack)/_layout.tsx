import { Tabs } from 'expo-router';
import { Platform } from 'react-native';

import HeaderLabel from '@/components/HeaderLabel'

export default function StackLayout() {
  return (
    <Tabs screenOptions={{
      tabBarActiveTintColor: '#ffd33d',
      headerTitle: () => <HeaderLabel />,
      headerStyle: {
        backgroundColor: '#25292e',
      },
      headerShadowVisible: false,
      headerTintColor: '#fff',
      tabBarStyle: {
        display: 'none',
      },
    }} />
  );
}