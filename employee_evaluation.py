class EmployeePerformanceExpertSystem:
    def __init__(self):
        self.criteria = {
            "productivity": "How would you rate the employee's productivity? (low/medium/high): ",
            "teamwork": "How well does the employee work in a team? (poor/average/excellent): ",
            "punctuality": "How punctual is the employee? (rarely/usually/always): ",
            "problem_solving": "How effective is the employee in solving problems? (weak/moderate/strong): "
        }
        self.performance_score = {
            "low": 1, "medium": 2, "high": 3,
            "poor": 1, "average": 2, "excellent": 3,
            "rarely": 1, "usually": 2, "always": 3,
            "weak": 1, "moderate": 2, "strong": 3
        }

    def evaluate_performance(self):
        score = 0
        max_score = len(self.criteria) * 3

        print("\nEmployee Performance Evaluation:\n")
        for criterion, question in self.criteria.items():
            while True:
                response = input(question).strip().lower()
                if response in self.performance_score:
                    score += self.performance_score[response]
                    break
                else:
                    print("Invalid input. Please enter a valid option.")

        percentage_score = (score / max_score) * 100

        if percentage_score >= 80:
            performance = "Excellent"
        elif percentage_score >= 60:
            performance = "Good"
        elif percentage_score >= 40:
            performance = "Average"
        else:
            performance = "Needs Improvement"

        print(f"\nOverall Performance Rating: {performance} ({percentage_score:.2f}%)\n")


if __name__ == "__main__":
    system = EmployeePerformanceExpertSystem()
    system.evaluate_performance()
