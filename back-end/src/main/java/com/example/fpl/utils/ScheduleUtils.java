package com.example.fpl.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleUtils {
	public static List<List<Integer>> fourTeamSchedule = new ArrayList<>();
	public static List<List<Integer>> sixTeamSchedule = new ArrayList<>();
	public static List<List<Integer>> eightTeamSchedule = new ArrayList<>();

	static
	{
		Integer[] firstWeek4 = new Integer[] {1,2,3,4};
		Integer[] secondWeek4 = new Integer[] {1,3,2,4};
		Integer[] thirdWeek4 = new Integer[] {1,4,2,3};
		fourTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(firstWeek4)));
		fourTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(secondWeek4)));
		fourTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(thirdWeek4)));
		
		Integer[] firstWeek6 = new Integer[] {1,2,3,4,5,6};
		Integer[] secondWeek6 = new Integer[] {1,3,2,5,4,6};
		Integer[] thirdWeek6 = new Integer[] {1,6,2,3,4,5};
		Integer[] fourthWeek6 = new Integer[] {1,4,2,6,3,5};
		Integer[] fifthWeek6 = new Integer[] {1,5,2,4,3,6};
		sixTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(firstWeek6)));
		sixTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(secondWeek6)));
		sixTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(thirdWeek6)));
		sixTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(fourthWeek6)));
		sixTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(fifthWeek6)));

		Integer[] firstWeek8 = new Integer[] {1,2,3,4,5,6,7,8};
		Integer[] secondWeek8 = new Integer[] {1,3,2,4,5,7,6,8};
		Integer[] thirdWeek8 = new Integer[] {1,4,2,3,5,8,6,7};
		Integer[] fourthWeek8 = new Integer[] {1,5,2,6,3,7,4,8};
		Integer[] fifthWeek8 = new Integer[] {1,6,2,5,3,8,4,7};
		Integer[] sixthWeek8 = new Integer[] {1,7,2,8,3,5,4,6};
		Integer[] seventhWeek8 = new Integer[] {1,8,2,7,3,6,4,5};
		
		eightTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(firstWeek8)));
		eightTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(secondWeek8)));
		eightTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(thirdWeek8)));
		eightTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(fourthWeek8)));
		eightTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(fifthWeek8)));
		eightTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(sixthWeek8)));
		eightTeamSchedule.add(new ArrayList<Integer>(Arrays.asList(seventhWeek8)));
		
	}
}
