package com.zettamine.isa.contoller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.zettamine.isa.dto.ScheduleInterview;
import com.zettamine.isa.dto.Status;
import com.zettamine.isa.service.impl.ScheduleInterviewService;
import com.zettamine.isa.view.dto.InterviewScheduleView;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class InterviewController
 */
public class InterviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InterviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		ScheduleInterviewService service = new ScheduleInterviewService();
		RequestDispatcher rd;
		switch(action) {
		case "schedule-new":
			System.out.println("case");
			ScheduleInterview schedInter = getScheduledInterview(request);
			service.save(schedInter);
			rd=request.getRequestDispatcher("/interview?action=showall");
			rd.include(request, response);
			break;
		case "showall":
			List<InterviewScheduleView> list = service.getAll();
			
			request.setAttribute("interviews", list);
			rd= request.getRequestDispatcher("interviews.jsp");
			rd.include(request, response);
			break;
		case "delete":
			ScheduleInterview obj = new ScheduleInterview();
			obj.setScheduleId(Integer.parseInt(request.getParameter("id")));
			service.delete(obj);
			rd=request.getRequestDispatcher("/interview?action=showall");
			rd.include(request, response);
			break;
			
			
			
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	static ScheduleInterview getScheduledInterview(HttpServletRequest request) {
		HttpSession s= request.getSession();
		s.setAttribute("recruiterId", 1);
		ScheduleInterview schedInter = new ScheduleInterview();
		schedInter.setApplicantId(Integer.parseInt(request.getParameter("applId")));
		schedInter.setInterviewerId(Integer.parseInt(request.getParameter("interviewerId")));
		String status=request.getParameter("status");
		
		schedInter.setInterviewStatus(Status.valueOf(status.toUpperCase()));
		HttpSession session = request.getSession();
		schedInter.setRecruiterId((Integer)session.getAttribute("recruiterId"));
		//Date date = Date.valueOf(LocalDate.parse(request.getParameter("date")));
		schedInter.setScheduledDate( Date.valueOf(LocalDate.parse(request.getParameter("date"))));
		schedInter.setScheduledTime(Time.valueOf(LocalTime.parse(request.getParameter("time"))));
		
		
		
		
		return schedInter;
		
	}

}
