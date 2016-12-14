package org.gradle.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckNum {
	public static Map<Integer, List<String>> numbers(){
		Map<Integer,List<String>> map= new HashMap<Integer,List<String>	>();
		
		//吸血数字
		for(int i = 999;i<10000;i++){
			if((i%100)!=0){
				int[] nums = new int[4];
				nums[0] = i/1000;
				nums[1] = (i-nums[0]*1000)/100;
				nums[2] = (i-nums[0]*1000-nums[1]*100)/10;
				nums[3] = i-nums[0]*1000-nums[1]*100-nums[2]*10;
				
				for(int j=0;j<nums.length;j++){
					if(nums[j]==0)continue;
					int temp=nums[j]*10;
					for(int k=0;k<nums.length;k++){
						if(k!=j){			//2187   81  27
							int n1=temp+nums[k];
							if(i%n1!=0)continue;//不整除
							int n2=i/n1;
							if(n2/100!=0)continue;//超过100
							int a2=n2/10;
							int b2=n2-a2*10;
							int count=0;
							for(int h=0;h<nums.length;h++){
								if(h==j||h==k)continue;
								if(nums[h]==a2){
									count++;
									for(int h1=0;h1<nums.length;h1++){
										if(h1==j||h1==k||h1==h)continue;
										if(nums[h1]==b2){
											count++;
											break;
										}
									}
									break;
								}
							}

							if(count==2){
								if(map.get(i)!=null){
									map.get(i).add(n1+"-"+n2);
								}else{
									List<String> list = new ArrayList<String>();
									list.add(n1+"-"+n2);
									map.put(i, list);
								}
							}

						}
					}
				}
				
			}
		}
		return map;
	}
}
